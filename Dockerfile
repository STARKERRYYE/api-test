FROM openjdk:11

RUN mkdir -p /kerry/app

COPY /target/api-0.0.1-SNAPSHOT.jar /kerry/app/api.jar

WORKDIR /kerry/app

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "api.jar"]