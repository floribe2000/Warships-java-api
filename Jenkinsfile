pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh "set +x"
                sh 'cp /var/tmp/Warships.properties Warships.properties'
                sh 'mvn clean package'
                archiveArtifacts '*/*.jar'
            }
        }

        stage('Deploy local') {
            steps {
                sh 'mvn install -DskipTests=true'
            }
        }

        stage('Deploy remote') {
            when {
                branch 'master'
            }
            steps {
                sh 'mvn deploy -DskipTests=true'
            }
        }

        stage('Create Documentation') {
            steps {
                sh 'mvn site'
            }
        }

    }
    tools {
        maven 'default-maven'
    }
}