pipeline {
  agent any
  stages {
    stage("copy files to ansible server") {
      steps {
        script {
          echo "copying all neccessary files to ansible control node"
          sshagent(['ansible-server-key']) {
            sh "scp -o StrictHostKeyChecking=no ansible/* root@46.101.170.88:/root" // cp to ansible server (droblet)
            withCredentials([sshUserPrivateKey(credentialsId: 'ec2-server-key', keyFileVariable: 'keyfile', usernameVariable: 'user')]) {
              sh 'scp $keyfile root@46.101.170.88:/root/ansible-jenkins.pem' // using this syntax to fix security issue
            }
          }
        }
      }
    }
    stage("execute ansible playbook") {
      steps {
        script {
          echo "calling ansible playbook to configure ec2 instances" // for this we install ssh pipeline steps plugin
          def remote = [:]
          remote.name = "ansible-server"
          remote.host = "46.101.170.88"
          remote.allowAnyHosts = true
          withCredentials([sshUserPrivateKey(credentialsId: 'ansible-server-key', keyFileVariable: 'keyfile', usernameVariable: 'user')]) {
            remote.user = user
            remote.identityFile = keyfile
            sshCommand remote: remote, command: "ls -l"
          }
        }
      }
    }
  }
}