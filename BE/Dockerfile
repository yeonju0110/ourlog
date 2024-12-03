# Use the official image for OpenJDK 17
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the Gradle build
ARG JAR_FILE=build/libs/ourlog-0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Expose the port Spring Boot app will run on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "/app/app.jar"]
