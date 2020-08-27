pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh './gradlew clean build -x test -x integrationTest -x checkstyleMain -x checkstyleTest'
            }
        }
        stage('Checkstyle') {
            steps {
                sh './gradlew checkstyleMain'
            }
        }
        stage('Unit tests') {
            steps {
                sh './gradlew test --info'
            }
        }
        stage('Integration tests') {
            steps {
                sh './gradlew integrationTest --info'
            }
        }
    }
    post {
        always {
            junit 'build/test-results/**/*.xml'
            checkstyle 'build/reports/checkstyle/*.xml'
        }
    }
}