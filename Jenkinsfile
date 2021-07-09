pipeline {
    agent any
    tools {
        gradle 'gradle69' 
    }

    def testBucket = 'gs://dataproc-staging-sa-east1-664534573047-ccfoqrdc/test'

    stages {
        stage('check version') {
            steps {
                sh 'gradle -version'
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