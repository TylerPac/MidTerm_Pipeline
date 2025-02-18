pipeline {
    agent {
        docker { image 'jenkins-with-maven' }  // Specify your custom image
    }

    environment {
        MAVEN_HOME = '/usr/share/maven'  // Correct Maven installation path
        PATH = "$MAVEN_HOME/bin:$PATH"  // Add Maven to PATH
        DOCKER_IMAGE = 'jenkins-with-maven'  // Custom Docker image
        DOCKER_CREDENTIALS_ID = 'docker-hub-credentials'  // Docker credentials
    }

    stages {
        stage('Checkout Code') {
            steps {
                git credentialsId: 'github-token', url: 'https://github.com/TylerPac/MidTerm_Pipeline.git', branch: 'main'
            }
        }

        stage('Check Maven Installation') {
            steps {
                sh 'mvn -version'  // Ensure Maven is available
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package'  // Build the project with Maven
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
