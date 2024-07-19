#FROM openjdk:11
#
#RUN mkdir -p /kerry/app
#
#COPY /target/api-0.0.1-SNAPSHOT.jar /kerry/app/api.jar
#
#WORKDIR /kerry/app
#
#EXPOSE 8082
#
#ENTRYPOINT ["java", "-jar", "api.jar"]


FROM jdk:11
VOLUME /tmp
ADD /target/api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8088
ENTRYPOINT ["Bash","-DBash.security.egd=file:/dev/./urandom","-jar","/app.jar"]