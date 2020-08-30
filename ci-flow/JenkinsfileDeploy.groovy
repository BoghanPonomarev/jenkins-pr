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
                sh 'sudo docker ps'
                sh 'sudo docker rmi $(docker images -q)'
                sh 'sudo docker build -t azazlovi4up/jenkins-pr:${BUILD_NUMBER} .'
                sh 'sudo docker push azazlovi4up/jenkins-pr:${BUILD_NUMBER}'
            }
        }
    }
}