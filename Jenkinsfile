pipeline {
    agent any

    environment {
        APP_IMAGE = 'gym-app'
        SELENIUM_IMAGE = 'gym-selenium'
        APP_CONTAINER = 'gym-container'
        MONGO_CONTAINER = 'mongo-container'
    }

    stages {

        stage('Code Build') {
            steps {
                echo 'Building the application...'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Unit Testing') {
            steps {
                echo 'Running unit tests...'
                sh 'mvn test -Dtest=GymControllerTest -DfailIfNoTests=false'
            }
        }

        stage('Containerized Deployment') {
            steps {
                echo 'Deploying application in Docker...'
                sh '''
                    docker stop ${APP_CONTAINER} || true
                    docker stop ${MONGO_CONTAINER} || true
                    docker rm ${APP_CONTAINER} || true
                    docker rm ${MONGO_CONTAINER} || true
                    docker network create gym-network || true
                    docker run -d \
                        --name ${MONGO_CONTAINER} \
                        --network gym-network \
                        mongo:6.0
                    docker build -t ${APP_IMAGE} .
                    docker run -d \
                        --name ${APP_CONTAINER} \
                        --network gym-network \
                        -p 8081:8080 \
                        -e SPRING_DATA_MONGODB_HOST=${MONGO_CONTAINER} \
                        ${APP_IMAGE}
                    sleep 15
                '''
            }
        }

        stage('Containerized Selenium Testing') {
            steps {
                echo 'Running Selenium tests in Docker...'
                sh '''
                    docker build -t ${SELENIUM_IMAGE} -f Dockerfile.selenium .
                    docker run --rm \
                        --network gym-network \
                        -e APP_URL=http://${APP_CONTAINER}:8080 \
                        ${SELENIUM_IMAGE}
                '''
            }
        }
    }

    post {
        always {
            echo 'Cleaning up...'
            sh '''
                docker stop ${APP_CONTAINER} || true
                docker stop ${MONGO_CONTAINER} || true
                docker rm ${APP_CONTAINER} || true
                docker rm ${MONGO_CONTAINER} || true
                docker network rm gym-network || true
            '''
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}