pipeline {
    agent any
        stages {

        stage('Dependency Check Analyze') {
            steps { 
             sh '/var/lib/jenkins/tools/org.jenkinsci.plugins.DependencyCheck.tools.DependencyCheckInstallation/Dependency-Check/bin/dependency-check.sh --scan "/var/lib/jenkins/workspace/go-app" --format XML --format HTML'
            }
        }

}
}
