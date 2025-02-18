pipeline {
    agent any

    tools {
        maven 'Maven 3' // This must match the name in your Jenkins Maven configuration
    }

    stages {
        stage('Cleanup Workspace') {
            steps {
                cleanWs()  // Deletes all files before fetching new code
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
                sh 'docker build -t my-maven-app .'
            }
        }

        stage('Deploy Application') {
            steps {
                sh 'docker run -d -p 8080:8080 my-maven-app'
            }
        }
    }
}
