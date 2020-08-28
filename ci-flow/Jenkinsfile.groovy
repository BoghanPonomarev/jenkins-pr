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
        stage('Init and migrate databse') {
            steps {
                sh 'mysql -u ${DB_USERNAME} -p${DB_PASSWORD} -h ${DB_URL} -P 3306'
                mysql 'DROP SCHEMA IF EXISTS jenkins_pr;'
                mysql 'CREATE SCHEMA jenkins_pr;'
                mysql 'source ./db_init.sql'
            }
        }
        stage('Integration tests') {
            steps {
                sh ''
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