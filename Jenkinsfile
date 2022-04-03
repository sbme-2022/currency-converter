pipeline {
  stages {
      stage('Test') {
          steps {
              sh './gradlew test'
          }
      }
      stage('Check') {
          steps {
              sh './gradlew check -x test'
          }

      }
      stage('Build') {
          steps {
              sh './gradlew build -x check -x test'
          }
      }
  }
}