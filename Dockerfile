FROM openjdk:11

RUN mkdir -p /kerry/app

COPY /target/api-0.0.1-SNAPSHOT.jar /app/api.jar

ENTRYPOINT ["java", "-jar", "/app/api.jar"]

EXPOSE 8082