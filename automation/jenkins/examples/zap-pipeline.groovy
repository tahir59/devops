pipeline {
    agent any
        stages {

        stage('ZAP Analyze') {
            steps {
                script {
                    startZap(host: "localhost", port: 8090, zapHome: "/var/lib/jenkins/tools/com.cloudbees.jenkins.plugins.customtools.CustomTool/zap/ZAP_2.8.1/", sessionPath:"/var/lib/jenkins/workspace/owasp-zap/zaptest.session")
                }
                script {
                    archiveZap(failAllAlerts: 10, failHighAlerts: 0, failMediumAlerts: 0, failLowAlerts: 0, falsePositivesFilePath: "zapFalsePositives.json")
                }
            }
        }
        
}
}
