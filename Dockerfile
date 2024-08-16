FROM openjdk:17-jdk-alpine

WORKDIR /demo

RUN mvn clean package

COPY /target/demo-0.0.1-SNAPSHOT.jar demo.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "demo.jar"]