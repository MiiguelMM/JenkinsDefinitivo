pipeline {
    agent any
    
    tools {
        maven 'Maven 3.9.9'
        jdk 'Java 21'
    }

    stages {
        stage('Verificar versión de Java') {
            steps {
                sh 'java -version'  // Muestra la versión de Java
            }
        }
        
        stage('Build') {
            steps {
                echo 'Compilando proyecto...'
                sh 'chmod +x ./mvnw || true'  // Intenta dar permisos, ignora errores
                sh './mvnw clean install || mvn clean install'  // Intenta con wrapper, si falla usa maven normal
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

        stage('Análisis de Código') {
            steps {
                echo 'Analizando código con SonarQube...'
                withSonarQubeEnv('SonarQube') {
                    sh './mvnw sonar:sonar || mvn sonar:sonar'
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
                // Comandos para desplegar en servidor de desarrollo
            }
        }

        stage('Desplegar en Producción') {
            when {
                branch 'main'
            }
            steps {
                echo 'Desplegando en producción...'
                // Comandos para desplegar en servidor de producción
            }
        }
    }

    post {
        success {
            echo 'Pipeline ejecutado con éxito!'
            // Enviar notificación de éxito
        }
        failure {
            echo 'El pipeline ha fallado!'
            // Enviar notificación de fallo
        }
    }
}