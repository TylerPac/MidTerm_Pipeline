pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'your-dockerhub-username/midterm_pipeline'
        DOCKER_CREDENTIALS_ID = 'docker-hub-credentials'
        MAVEN_HOME = "/usr/share/maven"
        PATH = "$MAVEN_HOME/bin:/usr/bin:/opt/java/openjdk/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/sbin:/bin"
    }

    stages {
        stage('Checkout Code') {
            steps {
                git credentialsId: 'github-token', url: 'https://github.com/TylerPac/MidTerm_Pipeline.git', branch: 'main'
            }
        }

        stage('Build with Maven') {
            steps {
                sh '/usr/bin/mvn clean package'  // Explicitly use Maven's path
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
