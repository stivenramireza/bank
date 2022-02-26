FROM openjdk:17.0.2-jdk-oracle
VOLUME [ "/tmp" ]
EXPOSE 9092
COPY customer-microservice-0.0.1.jar /customer-microservice-0.0.1.jar
ENTRYPOINT [ "java", "-jar", "customer-microservice-0.0.1.jar" ]
