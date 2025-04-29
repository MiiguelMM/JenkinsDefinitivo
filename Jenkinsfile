pipeline {
    agent any

    environment {
        SONAR_TOKEN = credentials('SONAR_AUTH_TOKEN')
    }
    
    tools {
        maven 'Maven 3.9.9'
        jdk 'Java 21'
    }

    stages {
        stage('Verificar versión de Java') {
            steps {
                sh 'java -version'
            }
        }
        
        stage('Build') {
            steps {
                echo 'Compilando proyecto...'
                sh 'chmod +x ./mvnw || true'
                sh './mvnw clean install || mvn clean install'
            }
        }

        stage('Tests') {
            steps {
                echo 'Ejecutando tests...'
                sh './mvnw test || mvn test'
            }
        }

        stage('Empaquetar') {
            steps {
                echo 'Empaquetando...'
                sh './mvnw package -DskipTests || mvn package -DskipTests'
            }
        }

        // ✅ CORREGIDO: Eliminamos "stages {}" adicional
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh "./mvnw clean verify sonar:sonar -Dsonar.login=$SONAR_TOKEN"
                }
            }
        }

        stage('Publicar en Nexus') {
            steps {
                echo 'Publicando artefactos en Nexus...'
                withCredentials([usernamePassword(credentialsId: 'nexus-admin', 
                                                 usernameVariable: 'NEXUS_USER', 
                                                 passwordVariable: 'NEXUS_PASSWORD')]) {
                    sh '''
                        ./mvnw deploy -DskipTests \
                        -DaltDeploymentRepository=nexus::default::http://localhost:8081/repository/maven-releases/ \
                        -DrepositoryId=nexus \
                        -Dusername=${NEXUS_USER} \
                        -Dpassword=${NEXUS_PASSWORD} || \
                        mvn deploy -DskipTests \
                        -DaltDeploymentRepository=nexus::default::http://localhost:8081/repository/maven-releases/ \
                        -DrepositoryId=nexus \
                        -Dusername=${NEXUS_USER} \
                        -Dpassword=${NEXUS_PASSWORD}
                    '''
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Desplegar en Desarrollo') {
            when {
                branch 'develop'
            }
            steps {
                echo 'Desplegando en ambiente de desarrollo...'
            }
        }

        stage('Desplegar en Producción') {
            when {
                branch 'main'
            }
            steps {
                echo 'Desplegando en producción...'
            }
        }
    }

    post {
        success {
            echo 'Pipeline ejecutado con éxito!'
        }
        failure {
            echo 'El pipeline ha fallado!'
        }
    }
}
