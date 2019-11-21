pipeline {
    agent any
        stages {

        stage('SonarQube Analyze') {
            steps {
             withSonarQubeEnv('sonarqube') {
             sh '/var/lib/jenkins/tools/hudson.plugins.sonar.SonarRunnerInstallation/SonarQubeScanner/bin/sonar-scanner -X -Dproject.settings=/var/lib/jenkins/workspace/go-app/sonar-scanner.properties'
            }
            }
        }

        stage('Delay 60s') {
            steps {
             sleep 60;
            }
        }

        stage('Quality Gate') {
            steps {
             timeout(time: 10, unit: 'MINUTES') {
             waitForQualityGate abortPipeline: true
            }
            }
        }

}
}
