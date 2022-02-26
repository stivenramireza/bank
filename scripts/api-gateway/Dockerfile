FROM openjdk:17.0.2-jdk-oracle
VOLUME [ "/tmp" ]
EXPOSE 8080
COPY api-gateway-0.0.1.jar /api-gateway-0.0.1.jar
ENTRYPOINT [ "java", "-jar", "api-gateway-0.0.1.jar" ]
