FROM openjdk:17.0.2-jdk-oracle
VOLUME [ "/tmp" ]
EXPOSE 9093
COPY user-microservice-0.0.1.jar /user-microservice-0.0.1.jar
ENTRYPOINT [ "java", "-jar", "user-microservice-0.0.1.jar" ]
