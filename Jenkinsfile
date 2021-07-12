def testBucket = "gs://dataproc-staging-sa-east1-664534573047-ccfoqrdc/test"
def prodBucket = "gs://dataproc-staging-sa-east1-664534573047-ccfoqrdc/jobs"


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
                sh "echo bucket for test deployment = ${testBucket}"
                sh "echo bucket for production deployment = ${prodBucket}"
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

        stage('deploy test build') {
            steps {
                sh "gcloud auth activate-service-account jenkins@total-amp-316818.iam.gserviceaccount.com --key-file=${secret_path} --project=total-amp-316818"
                sh "gsutil cp app/build/libs/app.jar ${testBucket}/app.jar"
            }
        }

        stage('test build') {
            steps {
                sh "gcloud auth activate-service-account jenkins@total-amp-316818.iam.gserviceaccount.com --key-file=${secret_path} --project=total-amp-316818"
                sh """gcloud dataproc jobs submit spark \
                       --cluster=klooster-03 \
                       --region=northamerica-northeast1 \
                       --class=org.apache.spark.examples.SparkPi \
                       --jars=${testBucket}/app.jar"""
            }
        }

        stage('deploy production build') {
            steps {
                sh "gcloud auth activate-service-account jenkins@total-amp-316818.iam.gserviceaccount.com --key-file=${secret_path} --project=total-amp-316818"
                sh "gsutil cp app/build/libs/app.jar ${prodBucket}/app.jar"
            }
        }

    }
}