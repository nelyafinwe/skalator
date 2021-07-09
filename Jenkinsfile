def testBucket = "gs://dataproc-staging-sa-east1-664534573047-ccfoqrdc/test"


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

        stage('check variable') {
            steps {
                sh "echo ${testBucket}"
            }
        }        

        stage('build') {
            steps {
                sh 'gradle build'
            }
        }  

        stage('run') {
            steps {
                sh 'gradle run'
            }
        }
    }
}