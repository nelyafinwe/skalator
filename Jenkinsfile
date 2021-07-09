node {
    // Get Artifactory server instance, defined in the Artifactory Plugin administration page.
    // def server = Artifactory.server "SERVER_ID"
    // Create an Artifactory Gradle instance.
    // def rtGradle = Artifactory.newGradleBuild()
    def productionBucket = 'gs://dataproc-staging-sa-east1-664534573047-ccfoqrdc/jobs'
    def testBucket = 'gs://dataproc-staging-sa-east1-664534573047-ccfoqrdc/test'
    
    stage('Clone sources') {
        git url: 'https://github.com/nelyafinwe/skalator.git'
    }

    }stage('Gradle build') {
        sh './gradlew clean build --no-daemon'
    }

    // stage('Publish test build') {
    //     sh "gsutil cp app/build/lib/app.tar ${testBucket}/kafka.tar"
    // }

    // stage('test job') {
    //     sh "gcloud spark-submit ${testBucket}/kafka.tar --packages --input --output"
    // }

    // stage('Publish build') {
    //     sh "gsutil cp app/build/lib/app.tar ${productionBucket}/kafka.tar"
    // }
}