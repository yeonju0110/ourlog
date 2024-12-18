# Use the official image for OpenJDK 17
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the Gradle build
ARG JAR_FILE=build/libs/ourlog-0-SNAPSHOT.jar
COPY ${JAR_FILE} /opt/app.jar

# Expose the port Spring Boot app will run on
EXPOSE 8080

# Debugging mode
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "/opt/app.jar"]

# Run the application
#CMD ["java", "-jar", "/app/app.jar"]
