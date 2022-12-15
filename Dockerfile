FROM openjdk:19-jdk
WORKDIR /usr/app
COPY ./target/nos-news-tracker-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "nos-news-tracker-0.0.1-SNAPSHOT.jar"]