pipeline {
    agent {
        docker {
            image 'maven:3.9.9-eclipse-temurin-22'
        }
    }
    environment {
        DOCKER_IMAGE = 'pigeonskyracedep'
        DOCKER_TAG = 'latest'
        SPRING_PROFILES_ACTIVE = 'prod'
        DATABASE_URL = 'jdbc:postgresql://postgres-container:5432/PSRDep'
    }
    stages {
        stage('Checkout Code') {
            steps {
                git credentialsId: 'your-git-credentials-id', url: 'https://github.com/your-repo.git'
            }
        }
        stage('Build with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKER_IMAGE}:${DOCKER_TAG}", '-f Dockerfile .')
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials-id') {
                        docker.image("${DOCKER_IMAGE}:${DOCKER_TAG}").push()
                    }
                }
            }
        }
        stage('Deploy to Docker Compose') {
            steps {
                sh '''
                docker-compose down
                docker-compose up -d
                '''
            }
        }
    }
}
