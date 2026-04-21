pipeline {
    agent any

    tools {
        maven 'maven'
    }

    environment {
        COMPOSE_PROJECT = "ecomm-microservices"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/garima0702/ecomm_microservices.git'
            }
        }

        stage('Build All Services') {
            steps {
                bat '''
                mvn clean package -DskipTests -f Ecomm_EurekaServer/pom.xml
                mvn clean package -DskipTests -f Ecomm_ConfigServer/pom.xml
                mvn clean package -DskipTests -f APIGateway/pom.xml

                mvn clean package -DskipTests -f Product/pom.xml
                mvn clean package -DskipTests -f ProductCatalog/pom.xml
                mvn clean package -DskipTests -f ProductInventory/pom.xml
                mvn clean package -DskipTests -f ProductPrice/pom.xml

                mvn clean package -DskipTests -f Cart/pom.xml
                mvn clean package -DskipTests -f Recommendation/pom.xml
                '''
            }
        }

        stage('Docker Build') {
            steps {
                bat '''
                docker build -t eureka ./Ecomm_EurekaServer
                docker build -t config ./Ecomm_ConfigServer
                docker build -t gateway ./APIGateway

                docker build -t product ./Product
                docker build -t catalog ./ProductCatalog
                docker build -t inventory ./ProductInventory
                docker build -t price ./ProductPrice

                docker build -t cart ./Cart
                docker build -t recommendation ./Recommendation
                '''
            }
        }

        stage('Docker Compose Deploy') {
            steps {
                bat '''
                docker-compose down || exit 0
                docker-compose up -d --build
                '''
            }
        }

    }

    post {
        success {
            echo "All services deployed using Docker Compose 🚀"
        }
        failure {
            echo "Pipeline failed ❌ check logs"
        }
    }
}
