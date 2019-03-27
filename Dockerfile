FROM gradle:jdk8 as builder
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x test

FROM openjdk:8-jdk-alpine

WORKDIR /app

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/urlshortener-app.jar"]