pipeline {
    agent any

    tools {
        maven 'maven'
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

        stage('Clean Previous Deployment') {
            steps {
                bat '''
                echo Cleaning old containers...
                docker-compose down --remove-orphans || exit 0
                docker rm -f eureka config gateway product catalog inventory price cart recommendation || exit 0
                '''
            }
        }

        stage('Deploy Using Docker Compose') {
            steps {
                bat '''
                echo Starting fresh deployment...
                docker-compose up -d --build
                '''
            }
        }

        stage('Verify Containers') {
            steps {
                bat 'docker ps'
            }
        }

    }

    post {
        success {
            echo "All services deployed successfully 🚀"
        }
        failure {
            echo "Pipeline failed ❌ check logs"
        }
    }
}
