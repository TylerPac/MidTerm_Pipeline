pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'your-dockerhub-username/midterm_pipeline'  // Replace with your actual Docker Hub username and image name
        DOCKER_CREDENTIALS_ID = 'docker-hub-credentials'           // Docker Hub credentials in Jenkins
        MAVEN_HOME = "/opt/maven"  // Set Maven path to the location inside the Jenkins container
        PATH = "$MAVEN_HOME/bin:$PATH"  // Add Maven to the PATH
    }

    stages {
        stage('Checkout Code') {
            steps {
                git credentialsId: 'github-token', url: 'https://github.com/TylerPac/MidTerm_Pipeline.git', branch: 'main'
            }
        }

        stage('Build with Maven') {
            steps {
                script {
                    // Make sure to run Maven with the correct path
                    sh "$MAVEN_HOME/bin/mvn clean package"  // Using Maven from $MAVEN_HOME
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Building Docker image using the specified tag
                    sh "docker build -t $DOCKER_IMAGE ."
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    // Push the Docker image to Docker Hub
                    withDockerRegistry([credentialsId: "$DOCKER_CREDENTIALS_ID", url: "https://index.docker.io/v1/"]) {
                        sh "docker push $DOCKER_IMAGE"
                    }
                }
            }
        }
    }
}
