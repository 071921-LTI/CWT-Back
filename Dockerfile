FROM openjdk:8-jdk-alpine
COPY target/Project2.jar Project2.jar
ENTRYPOINT ["java", "-jar", "/Project2.jar"]
