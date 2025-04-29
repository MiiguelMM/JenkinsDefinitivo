pipeline {
    agent any

    environment {
        // Usamos withCredentials para manejar SONAR_TOKEN de forma segura
        // en lugar de environment
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

        stage('SonarQube Analysis') {
            steps {
                // Capturamos errores para que el pipeline continúe incluso si SonarQube falla
                catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                    withSonarQubeEnv('SonarQube') {
                        // Usamos withCredentials para manejar el token de forma segura
                        withCredentials([string(credentialsId: 'SONAR_AUTH_TOKEN', variable: 'SONAR_TOKEN')]) {
                            // Especificamos la URL correcta y pasamos el token de forma segura
                            sh './mvnw clean verify sonar:sonar -Dsonar.host.url=http://sonarqube:9000 -Dsonar.login=$SONAR_TOKEN'
                        }
                    }
                }
            }
        }

        stage('Publicar en Nexus') {
            steps {
                echo 'Publicando artefactos en Nexus...'
                withCredentials([usernamePassword(credentialsId: 'nexus-admin', 
                                                 usernameVariable: 'NEXUS_USER', 
                                                 passwordVariable: 'NEXUS_PASSWORD')]) {
                    // Corregimos la URL de Nexus para usar el nombre del contenedor
                    sh '''
                        ./mvnw deploy -DskipTests \
                        -DaltDeploymentRepository=nexus::default::http://nexus:8081/repository/maven-releases/ \
                        -DrepositoryId=nexus \
                        -Dusername=${NEXUS_USER} \
                        -Dpassword=${NEXUS_PASSWORD} || \
                        mvn deploy -DskipTests \
                        -DaltDeploymentRepository=nexus::default::http://nexus:8081/repository/maven-releases/ \
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
                    // Hacemos que esto sea opcional en caso de que SonarQube haya fallado
                    catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                        waitForQualityGate abortPipeline: false
                    }
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