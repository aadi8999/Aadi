pipeline {
    agent any

    environment {
        AWS_REGION = 'us-east-1' 
        INSTANCE_TYPE = 't2.micro' 
        AMI_ID = 'ami-0e2c8caa4b6378d8c'  
        KEY_NAME = 'Ubunbtu'  
        SECURITY_GROUP = 'sg-0e32ca3c3ec831e94' 
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
