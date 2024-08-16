FROM maven:3.8-openjdk-8 as stage1

WORKDIR /demo

COPY . .

RUN mvn clean package

USER  aziz

#Stage 2

FROM openjdk:17-jdk-alpine

WORKDIR /demo

EXPOSE 8080

COPY --from=stage1 /demo/target/*.jar demo.jar

ENTRYPOINT ["java","-jar","demo.jar"]