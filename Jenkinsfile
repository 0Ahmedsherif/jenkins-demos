// in order to use our defined function from the library we need to import the lib that we defined in the global
// config of jenkins
// syntax : @library('$NAME_OF_THE-LIB') || @library('$NAME_OF_THE-LIB')_ 
// if there's no defined vars after the importing of lib we need to add _ to seperate.
@Library('jenkins-shared-library')
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
          Buildjar()  // calling the func that we defined in the lib
        }
      }
    }
    stage("Build image") {
      steps {
        script {
          Buildimage "doc299/java-maven-app:2.1"
          }
        }
      }
    stage("Deploy") {
      steps {
        script {
          gv.deployapp()
        }
      }
    }
  }
}
