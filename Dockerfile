# Use a lightweight base image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file into our /app directory
COPY target/order_service-0.0.1-SNAPSHOT.jar ./order_service_app.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "order_service_app.jar"]

# Expose the port used by the application
EXPOSE 9195