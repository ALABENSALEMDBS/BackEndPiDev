pipeline {
    agent any
    
    tools {
        maven 'M2_HOME'
    }

    environment {
        SONARQUBE_ENV = 'sq_env' // le nom que tu as déjà configuré dans Jenkins
    }

    stages {
        stage('Hello') {
            steps {
                echo 'Hello World'
            }
        }

        stage('GIT') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/ALABENSALEMDBS/BackEndPiDev.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

       

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sq_env') {
                    sh 'mvn sonar:sonar'
                }
            }
        }
    }
}


