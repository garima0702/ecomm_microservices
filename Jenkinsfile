pipeline {
    agent any

    tools {
        maven 'maven'  
    }

    environment {
        IMAGE_PREFIX = "microservices"
        NETWORK = "ecomm-network"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/garima0702/ecomm_microservices.git'
            }
        }

        stage('Build All Services') {
            steps {
                bat 'mvn clean package -DskipTests -f Ecomm_EurekaServer/pom.xml'
                bat 'mvn clean package -DskipTests -f Ecomm_ConfigServer/pom.xml'
                bat 'mvn clean package -DskipTests -f APIGateway/pom.xml'

                bat 'mvn clean package -DskipTests -f Product/pom.xml'
                bat 'mvn clean package -DskipTests -f ProductCatalog/pom.xml'
                bat 'mvn clean package -DskipTests -f ProductInventory/pom.xml'
                bat 'mvn clean package -DskipTests -f ProductPrice/pom.xml'

                bat 'mvn clean package -DskipTests -f Cart/pom.xml'
                bat 'mvn clean package -DskipTests -f Recommendation/pom.xml'
            }
        }

        stage('Docker Build') {
            steps {
                bat 'docker build -t eureka ./Ecomm_EurekaServer'
                bat 'docker build -t config ./Ecomm_ConfigServer'
                bat 'docker build -t gateway ./APIGateway'

                bat 'docker build -t product ./Product'
                bat 'docker build -t catalog ./ProductCatalog'
                bat 'docker build -t inventory ./ProductInventory'
                bat 'docker build -t price ./ProductPrice'

                bat 'docker build -t cart ./Cart'
                bat 'docker build -t recommendation ./Recommendation'
            }
        }

        stage('Create Network') {
            steps {
                bat 'docker network create ecomm-network || exit 0'
            }
        }

        stage('Stop Old Containers') {
            steps {
                bat 'docker rm -f eureka config gateway product catalog inventory price cart recommendation || exit 0'
            }
        }

        stage('Run Containers') {
            steps {
                bat 'docker run -d --network ecomm-network -p 8761:8761 --name eureka eureka'
                bat 'docker run -d --network ecomm-network -p 8888:8888 --name config config'
                bat 'docker run -d --network ecomm-network -p 8081:8080 --name gateway gateway'

                bat 'docker run -d --network ecomm-network --name product product'
                bat 'docker run -d --network ecomm-network --name catalog catalog'
                bat 'docker run -d --network ecomm-network --name inventory inventory'
                bat 'docker run -d --network ecomm-network --name price price'

                bat 'docker run -d --network ecomm-network -p 8082:8082 --name cart cart'
                bat 'docker run -d --network ecomm-network -p 8083:8083 --name recommendation recommendation'
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
