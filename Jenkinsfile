pipeline {
    agent any

    environment {
        MAVEN_HOME = '/usr/share/maven'  // Correct Maven installation path
        PATH = "$MAVEN_HOME/bin:$PATH"  // Add Maven to PATH
        DOCKER_IMAGE = 'your-dockerhub-username/midterm_pipeline'
        DOCKER_CREDENTIALS_ID = 'docker-hub-credentials'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git credentialsId: 'github-token', url: 'https://github.com/TylerPac/MidTerm_Pipeline.git', branch: 'main'
            }
        }
        stage('Check Maven Installation') {
            steps {
                sh 'mvn -version'
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package'  // Explicitly run Maven
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t $DOCKER_IMAGE ."
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withDockerRegistry([credentialsId: "$DOCKER_CREDENTIALS_ID", url: ""]) {
                    sh "docker push $DOCKER_IMAGE"
                }
            }
        }
    }
}
