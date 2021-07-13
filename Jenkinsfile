def testBucket = "gs://dataproc-staging-sa-east1-664534573047-ccfoqrdc/test"
def prodBucket = "gs://dataproc-staging-sa-east1-664534573047-ccfoqrdc/jobs"


pipeline {
    agent any
    tools {
        gradle 'gradle69' 
    }

    environment {
        secret_path = credentials('jenkins')
        thisCommit = sh(returnStdout: true, script:'git rev-parse HEAD').trim()
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

        stage('deploy test build') {
            steps {
                sh "gcloud auth activate-service-account jenkins@total-amp-316818.iam.gserviceaccount.com --key-file=${secret_path} --project=total-amp-316818"
                sh "gsutil cp app/build/libs/app.jar ${testBucket}/app-${thisCommit}.jar"
            }
        }

        stage('test build') {
            steps {
                sh "gcloud auth activate-service-account jenkins@total-amp-316818.iam.gserviceaccount.com --key-file=${secret_path} --project=total-amp-316818"
                sh """gcloud dataproc jobs submit spark \
                       --id jenkins-build-${thisCommit}-\$(date +%s) \
                       --cluster=klooster-03 \
                       --region=northamerica-northeast1 \
                       --class=com.npg.skalator.App \
                       --jars=${testBucket}/app-${thisCommit}.jar \
                       --properties spark.jars.packages=org.apache.spark:spark-sql-kafka-0-10_2.12:3.1.1 \
                       -- test ${testBucket}/out_app-${thisCommit}
                       """
            }
        }

        stage('remove test build output') {
            steps {
                sh "gcloud auth activate-service-account jenkins@total-amp-316818.iam.gserviceaccount.com --key-file=${secret_path} --project=total-amp-316818"
                sh "gsutil rm -r ${testBucket}/out_app-${thisCommit}"
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