pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'set +x'
                sh 'cp /var/tmp/Warships.properties Warships.properties'
                sh './gradlew clean build'
                archiveArtifacts 'build/libs/*.jar'
            }
        }

        stage('Deploy local') {
            steps {
                sh './gradlew publish'
            }
        }

    }
    post {
        always {
            junit 'build/test-results/test/*.xml'
            javadoc(keepAll: true, javadocDir: 'build/dokka/javadoc')
        }
    }
    tools {
        jdk 'Java8'
    }
}