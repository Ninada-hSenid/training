# Use a base image with Java 21
FROM eclipse-temurin:21-jdk-jammy

# Add metadata
LABEL maintainer="ninada.m@hsenidlanka.com"

# Create app directory
WORKDIR /app

# Copy the jar file into the image
COPY target/Docker-Assignment-1.0-SNAPSHOT.jar app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
