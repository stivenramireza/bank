FROM openjdk:17.0.2-jdk-oracle
VOLUME [ "/tmp" ]
EXPOSE 9091
COPY balance-microservice-0.0.1.jar /balance-microservice-0.0.1.jar
ENTRYPOINT [ "java", "-jar", "balance-microservice-0.0.1.jar" ]
