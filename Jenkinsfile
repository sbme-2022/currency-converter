pipeline {
  stages {
      stage('Test') {
          sh './gradlew test'
      }
      stage('Check') {
          sh './gradlew check -x test'
      }
      stage('Build') {
          sh './gradlew build -x check -x test'
      }
  }
}