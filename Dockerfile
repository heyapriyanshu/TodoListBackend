# Stage 1: Build the application
FROM ubuntu:latest AS build

# Install dependencies
RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven

# Set the working directory
WORKDIR /app

# Copy the source code
COPY . .

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Expose the port the application runs on
EXPOSE 8080

# Copy the packaged jar file from the build stage
COPY --from=target /target/TodoBackendExec.jar app.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
