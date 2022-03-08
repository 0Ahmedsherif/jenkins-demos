pipeline {
  agent any
  stages {
    stage("copy files to ansible server") {
      steps {
        script {
          echo "copying all neccessary files to ansible control node"
          sshagent(['ansible-server-key']) {
            sh "scp -o StrictHostKeyChecking=no ~/jenkins-demo/ansible/* root@46.101.170.88:/root" // cp to ansible server (droblet)
            withCredentials([sshUserPrivateKey(credentialsId: 'ec2-server-key', keyFileVariable: 'keyfile', usernameVariable: 'user')]) {
              sh "scp ${keyfile} root@46.101.170.88:/root/ansible-jenkins.pem "
            }
          }
        }
      }
    }
  }
}