FROM payara/server-web:6.2024.1-jdk17
COPY target/api-rest-ejemplo.war $DEPLOY_DIR
