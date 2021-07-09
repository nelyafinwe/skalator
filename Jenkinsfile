def testBucket = "gs://dataproc-staging-sa-east1-664534573047-ccfoqrdc/test"


pipeline {
    agent any
    tools {
        gradle 'gradle69' 
    }

    environment {
        secret_path = credentials('jenkins')
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

        stage('deploy build') {
            steps {
                sh "gcloud auth activate-service-account jenkins@total-amp-316818.iam.gserviceaccount.com --key-file=${secret_path} --project=total-amp-316818"
                sh "gsutil cp app/build/libs/app.jar ${testBucket}/app.jar"
            }
        }

    }
}