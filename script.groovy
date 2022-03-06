def buildjar() {
  echo "Building the app..."
  sh 'mvn package'
}

def buildimage() {
  echo "Building the docker image..."
  withCredentials([usernamePassword(credentialsId: 'docker_hub', passwordVariable: 'PWD', usernameVariable: 'USER')]) {
    sh "echo $PwD | docker login -u $USER --password-stdin"
    sh 'docker build -t doc299/java-maven-app:2.2 .'
    sh 'docker push doc299/java-maven-app:2.2'
  }
}

def deployapp() {
  echo 'Deploying the app...'
  echo 'Deploying the on br $BRANCH_NAME'
}
return this
