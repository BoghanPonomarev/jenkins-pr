pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh './gradlew clean build -x test -x integrationTest -x checkstyleMain -x checkstyleTest ' +
                        '-x checkstyleIntegrationTest'
            }
        }
        stage('Build docker image') {
            steps {
                dir('./') {
                    sh 'docker ps'
                    sh 'docker rmi $(docker images -q)'
                    sh 'docker build -t azazlovi4up/jenkins-pr:${BUILD_NUMBER} .'
                    sh 'docker push azazlovi4up/jenkins-pr:${BUILD_NUMBER}'
                }
            }
        }
    }
}