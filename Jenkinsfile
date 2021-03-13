pipeline {
    agent any
    environment {
        TEST_WG_API_KEY = credentials("test-wg-api-key")
    }
    stages {
        stage('Build') {
            steps {
                sh('set +x')
                sh('./gradlew clean build -PtestApiKey=$TEST_WG_API_KEY')
                archiveArtifacts('build/libs/*.jar')
            }
        }

        stage('Deploy local') {
            steps {
                sh('./gradlew publish -PtestApiKey=$TEST_WG_API_KEY')
            }
        }

    }
    post {
        always {
            junit('build/test-results/test/*.xml')
            javadoc(keepAll: true, javadocDir: 'build/dokka/javadoc')
        }
    }
    tools {
        jdk 'Java8'
    }
}