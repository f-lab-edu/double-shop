def ssh_publisher(SERVER_CONFIG) {
    sshPublisher(
        continueOnError: false,
        failOnError: true,
        publishers:[
            sshPublisherDesc(
                configName: "${SERVER_CONFIG}",
                verbose: true,
                transfers: [
                    sshTransfer(
                        execCommand: "sudo sh deploy.sh"
                    )
                ]
            )
        ]
    )
}

pipeline {
    agent any
    tools {
        maven "Maven 3.8.4"
    }
    parameters {
        gitParameter branchFilter: 'origin/(.*)', defaultValue: 'main', name: 'CURRENT', type: 'PT_BRANCH'
    }
    stages {
        stage('clone') {
            steps {
                git url: "https://github.com/f-lab-edu/double-shop.git",
                    branch: "${params.BRANCH}"
                    sh "ls -al"
            }
        }
        stage('build') {
            steps {
                sh "pwd"
                sh "mvn --batch-mode --update-snapshots clean package -DskipTests"
                sh "ls -al"
                sh "sudo sh /root/build.sh"
            }
        }
        stage('deploy') {
            steps {
                sh "pwd"
                sh "ls -al"
                script {
                    def server_list = ["was1", "was2"]
                    for (server in server_list) {
                        stage(server) {
                            ssh_publisher(server)
                        }
                    }
                    stage("load_balancer") {
                        sh "pwd"
                        sh "sudo sh /root/deploy.sh"
                    }
                }
            }
        }
    }
}
