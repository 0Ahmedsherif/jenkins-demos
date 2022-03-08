pipeline {
  agent any
  environment {
    ANSIBLE_SERVER = "46.101.170.88"
  }
  stages {
    stage("copy files to ansible server") {
      steps {
        script {
          echo "copying all neccessary files to ansible control node"
          sshagent(['ansible-server-key']) {
            sh "scp -o StrictHostKeyChecking=no ansible/* root@${ANSIBLE_SERVER}:/root" // cp to ansible server (droblet)
            withCredentials([sshUserPrivateKey(credentialsId: 'ec2-server-key', keyFileVariable: 'keyfile', usernameVariable: 'user')]) {
              sh 'scp $keyfile root@$ANSIBLE_SERVER:/root/ansible-jenkins.pem' // using this syntax to fix security issue
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
          remote.host = ANSIBLE_SERVER
          remote.allowAnyHosts = true
          withCredentials([sshUserPrivateKey(credentialsId: 'ansible-server-key', keyFileVariable: 'keyfile', usernameVariable: 'user')]) {
            remote.user = user
            remote.identityFile = keyfile
            sshScript remote: remote, script: "auto-prepare-ansible-server.sh"
            sshCommand remote: remote, command: "ansible-playbook my-playbook.yml"
          }
        }
      }
    }
  }
}