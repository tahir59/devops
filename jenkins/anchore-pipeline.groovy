pipeline {
    agent any
        stages {

        stage('Anchore Analyze') {
            steps {
             sh 'echo "repository/myapp:latest" > anchore_images'
             anchore name: 'anchore_images', bailOnFail: 'false'
            }
        }

}
}
