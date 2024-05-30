FROM openjdk:17-jdk
ARG JAR_FILE=target/ms-chat-socket-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
COPY serviceAccountKey.json ./
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080