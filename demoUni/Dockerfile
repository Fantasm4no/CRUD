FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/demoUni-0.0.1-SNAPSHOT.jar uniApp.jar
ENTRYPOINT ["java", "-jar", "uniApp.jar"]
