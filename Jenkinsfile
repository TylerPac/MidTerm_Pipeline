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

        stage('Verify Build Artifacts') {
            steps {
                bat 'if not exist target\\my-app-1.0-SNAPSHOT.jar exit 1'
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
                bat '''
                docker stop -f %CONTAINER_NAME% || echo "No running container to stop"
                docker rm %CONTAINER_NAME% || echo "No existing container to remove"
                docker run -d --name %CONTAINER_NAME% -p 9090:9090 %DOCKER_IMAGE%
                '''
            }
        }
        stage('Debugging Info') {
            steps {
                bat 'docker ps -a'
                bat 'docker logs %CONTAINER_NAME%'
            }
        }

    }
}
