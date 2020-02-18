FROM openjdk:latest
COPY ./target/sem-group8-0.1.0.4-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "sem-group8-0.1.0.4-jar-with-dependencies.jar"]