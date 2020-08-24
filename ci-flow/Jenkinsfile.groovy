pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh './gradlew clean build -x test'
            }
        }
        stage('Unit tests') {
          steps {
                sh './gradlew test --info'
          }
        }
    }
    post {
            always {
                junit 'build/test-results/**/*.xml'
            }
     }
}