pipeline {
    agent any

    stages {
        stage('Cleanup Workspace') {
            steps {
                cleanWs()  // Deletes everything before pulling fresh code
            }
        }
        stage('Checkout Code') {
            steps {
                git branch: 'main', credentialsId: 'github-token', url: 'https://github.com/TylerPac/MidTerm_Pipeline.git'
            }
        }
    }
}
