pipeline {
  agent none

  stages {
    stage("test") {  // the test stage will executed in all branches
      steps {
        script {
          echo "Testing the app..."
        }
      }
    }
    stage("Build") {
      when {
        expression {
          BRANCH_NAME == 'master' // the build will only executed in the master br
        }
      }
      steps {
        script {
          echo "Building the app..."
          echo "executing pipeline for branch $BRANCH_NAME"
        }
      }
    }
    stage("Deploy") {
      when {
        expression {
          BRANCH_NAME == 'master' // the build will only executed in the master br
        }
      }
      steps {
        script {
          echo "Deploying the app..."
          echo "deploying pipeline for branch $BRANCH_NAME"
        }
      }
    }
  }
}