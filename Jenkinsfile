pipeline {
    agent any

    tools {
        maven 'Maven 3' // Ensure this matches the name in Jenkins
    }

    environment {
        DOCKER_IMAGE = 'my-maven-app'
        CONTAINER_NAME = 'maven-app-container'
    }

    stages {
        stage('Cleanup Workspace') {
            steps {
                cleanWs()
            }
        }

        stage('Checkout Code') {
            steps {
                git branch: 'main', credentialsId: 'github-token', url: 'https://github.com/TylerPac/MidTerm_Pipeline.git'
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Run Unit Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $DOCKER_IMAGE .'
            }
        }

        stage('Deploy Application') {
            steps {
                // Stop and remove existing container (if running)
                sh '''
                docker stop $CONTAINER_NAME || true
                docker rm $CONTAINER_NAME || true
                '''

                // Run new container
                sh 'docker run -d --name $CONTAINER_NAME -p 8080:8080 $DOCKER_IMAGE'
            }
        }
    }
}
