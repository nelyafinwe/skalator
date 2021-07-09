pipeline {
    agent any
    tools {
        gradle 'gradle69' 
    }
    stages {
        stage('check version') {
            steps {
                sh 'gradle -version'
            }
        }

        stage('build version') {
            steps {
                sh 'gradle build'
            }
        }        
    }
}