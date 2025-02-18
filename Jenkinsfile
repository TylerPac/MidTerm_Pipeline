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
                bat 'mvn clean package'
            }
        }

        stage('Run Unit Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker build -t %DOCKER_IMAGE% .'
            }
        }

        stage('Deploy Application') {
            steps {
                // Stop and remove existing container (if running)
                bat '''
                docker stop %CONTAINER_NAME% || exit 0
                docker rm %CONTAINER_NAME% || exit 0
                '''

                // Run new container with a different external port (e.g., 9090)
                bat 'docker run -d --name %CONTAINER_NAME% -p 9090:9090 %DOCKER_IMAGE%'
            }
        }
    }
}
