pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'your-dockerhub-username/midterm_pipeline'
        DOCKER_CREDENTIALS_ID = 'docker-hub-credentials'
        MAVEN_HOME = "/usr/share/maven"  // Set the correct Maven path
        PATH = "$MAVEN_HOME/bin:$PATH"
    }

    stages {
        stage('Checkout Code') {
            steps {
                git credentialsId: 'github-token', url: 'https://github.com/TylerPac/MidTerm_Pipeline.git', branch: 'main'
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package'  // Jenkins should now find Maven
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
