pipeline {
   agent any

   environment {
      KUBECONFIG="/opt/kube.conf"
   }

   stages {
      stage('Launching Temp Containers') {
         steps {
            sh 'kubectl run psql-bkp-cont --image=postgres:latest --env="PGPASSWORD=xxxxxxxx" --command sleep infinity --restart=Never'
            sh 'kubectl run psql-tst-cont --image=postgres:latest --env="PGPASSWORD=xxxxxxxx" --command sleep infinity --restart=Never'
            sh 'kubectl scale --replicas=1 statefulset.apps/pgsql-test-postgresql'
            sh 'sleep 60'
         }
      }
      stage('Taking Backup') {
         steps {
            sh 'kubectl exec psql-bkp-cont -- bash -c "pg_dump -h host -p port -U username dbname" > dbname.sql'
         }
      }
      stage('Testing Backups') {
         steps {
            sh """
               kubectl exec psql-tst-cont -- bash -c "psql -h host -p port -U username -c 'create database dbname' "
            """
            sh 'kubectl exec -i psql-tst-cont -- bash -c "psql -h host -p port -U username dbname" < dbname.sql'
            sh """
               kubectl exec psql-tst-cont -- bash -c "psql -h host -p port -U username -c 'drop database dbname' "
            """
         }
      }
      stage('Saving Backups on Ceph') {
         steps {
            sh 'mv dbname.sql dbname.sql-`date +%F`'
            sh 's3cmd put dbname.sql-`date +%F` s3://bucketname'
         }
      }        
      stage('Cleanup Temp Containers') {
         steps {
            sh 'kubectl delete pod/psql-bkp-cont && kubectl delete pod/psql-tst-cont && kubectl scale --replicas=0 statefulset.apps/pgsql-test-postgresql && rm -rf dbname.sql && rm -rf dbname.sql-`date +%F`'
         }
      }
   }

   post {
        failure {
            sh 'kubectl delete pod/psql-bkp-cont'
            sh 'kubectl delete pod/psql-tst-cont'
            sh 'kubectl scale --replicas=0 statefulset.apps/pgsql-test-postgresql'
            sh 'rm -rf dbname.sql'
            sh 'rm -rf dbname.sql-`date +%F`'
            sh """        
              curl -X POST http://host/api/v1/webhooks/url -d '{"status": "fail"}' -H 'Content-Type: application/json' -H 'St2-Api-Key: xxxxxxxx'
            """
            }
        }

}