FROM openjdk:11-slim

COPY ./build/libs/coronavirusapi-0.0.1-SNAPSHOT.war /usr/app/app.war
WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "app.war"]