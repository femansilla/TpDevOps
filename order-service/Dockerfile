# Use a lightweight base image
FROM openjdk:18-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file into our /app directory
COPY target/ordersystem-0.0.1-SNAPSHOT.jar ./ordersystem-0.0.1-SNAPSHOT.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "ordersystem-0.0.1-SNAPSHOT.jar"]

# Expose the port used by the application
EXPOSE 9195