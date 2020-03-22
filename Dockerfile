FROM openjdk:11-slim

COPY ./build/libs/coronavirusapi-0.0.1-SNAPSHOT.jar /usr/app/app.jar
WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "app.jar"]