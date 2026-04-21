pipeline {
    agent any

    tools {
        maven 'maven'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/garima0702/ecomm_microservices.git'
            }
        }

        stage('Build JARs') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                bat 'docker-compose down -v'
                bat 'docker-compose up --build -d'
            }
        }
    }

    post {
        success {
            echo "All microservices deployed successfully"
        }
        failure {
            echo "Build failed — check logs"
        }
    }
}
