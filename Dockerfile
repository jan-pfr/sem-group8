FROM openjdk:latest
COPY ./target/PopStatsApp.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "PopStatsApp.jar", "db:3306", "headless"]