pipeline {
    agent any
        stages {

        stage('GIT Checkout') {
            steps { 
             echo "GIT Checkout"
            }
        }

        stage('Creating Build') {
            steps { 
             sh 'docker build -t repository/myapp:latest .'
            }
        }

        stage('Pushing Image to Repository') {
            steps { 
             sh 'docker push repository/myapp:latest'
            }
        }            

        stage('Deploying Image') {
            steps { 
             sh 'kubectl set image deployment/myapp myapp=repository/myapp:latest'
            }
        }

        post {
             failure {
             emailext (
                 subject: "Pipeline Failed",
                 body: "Build URL: $BUILD_URL",
                 to: "myemail@email.com;",
                 from: "jenkins@email.com"
                 )
                 }
             success {
             emailext (
                 subject: "Pipeline Passed",
                 body: "Build URL: $BUILD_URL",
                 to: "myemail@email.com;",
                 from: "jenkins@email.com"
                 )
                 }
             }   

}
}
