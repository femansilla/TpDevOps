# Use a base image with JDK
FROM maven:3.9.9-eclipse-temurin-21 AS builder

# Set working directory
WORKDIR /app

# Copy the project files
COPY . .

# Build the application and create the JAR file
RUN mvn clean package -DskipTests

# Use a minimal runtime image
FROM openjdk:22-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the port your application runs on (if necessary)
EXPOSE 8081

# Command to run the application
CMD ["java", "-jar", "app.jar"]