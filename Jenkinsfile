// this is a pipeline to build a jar file and packaging it and build a docker images from it and finally pushing it into dockerhub repo.
def gv
pipeline {
  agent any
  tools {
    maven 'M3'
  }
  stages {
    stage("Init") {
      steps {
        script {
          gv = load "script.groovy"
        }
      }
    }
    stage("Build jar") {
      steps {
        script {
          gv.buildjar()
        }
      }
    }
    stage("Build image") {
      steps {
        script {
          gv.buildimage()
          }
        }
      }
    stage("Deploy") {
      // when {
      //   expression {
      //     BRANCH_NAME == 'master'
      //   }
      }
      steps {
        script {
          gv.deployapp()
        }
      }
    }
  }
}
