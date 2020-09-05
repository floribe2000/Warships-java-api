pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'set +x'
        sh 'cp /var/tmp/Warships.properties Warships.properties'
        sh 'mvn clean package site'
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

    stage('Publish Documentation') {
      steps {
        junit 'target/site/surefire-report.html'
        javadoc(keepAll: true, javadocDir: 'target/site/apidocs')
      }
    }

  }
  tools {
    maven 'default-maven'
  }
}