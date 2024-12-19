pipeline {
    agent any

    environment {
        AWS_REGION = 'us-east-1'  // Replace with your desired AWS region
        INSTANCE_TYPE = 't2.micro'  // Replace with your desired instance type
        AMI_ID = 'ami-0e2c8caa4b6378d8c'  // Replace with your desired AMI ID
        KEY_NAME = 'Ubunbtu'  // Replace with your EC2 key pair name
        SECURITY_GROUP = 'sg-0dfc65a8a78a0eacd'  // Replace with your security group ID
    }

    stages {
        stage('Setup AWS CLI') {
            steps {
                sh 'sudo apt update'
                sh 'sudo apt install -y awscli'
            }
        }

        stage('Launch EC2 Instance') {
            steps {
                script {
                    sh """
                    aws ec2 run-instances \
                        --region ${AWS_REGION} \
                        --instance-type ${INSTANCE_TYPE} \
                        --image-id ${AMI_ID} \
                        --key-name ${KEY_NAME} \
                        --security-group-ids ${SECURITY_GROUP}
                    """
                }
            }
        }
    }
}
