// this is a pipeline to build a jar file and packaging it and build a docker images from it and finally pushing it into dockerhub repo.
pipeline {
  agent any
  tools {
    maven 'M3'
  }
  stages {
    stage("Build jar") {
      steps {
        script {
          echo "Building the app..."
          sh 'mvn package'
        }
      }
    }
    stage("Build image") {
      steps {
        script {
          echo "Building the docker image..."
          withCredentials([usernamePassword(credentialsId: 'docker_hub', passwordVariable: 'PWD', usernameVariable: 'USER')]) {
            sh "echo $PwD | docker login -u $USER --password-stdin"
            sh 'docker build -t doc299/java-maven-app:1.1 .'
            sh 'docker push doc299/java-maven-app:1.1'
          }
        }
      }
    }
    stage("Deploy") {
      steps {
        script {
          echo 'Deploying the app...'
        }
      }
    }
  }
}