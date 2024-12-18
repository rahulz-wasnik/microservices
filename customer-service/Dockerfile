# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
#WORKDIR /app

# Copy the Spring Boot application JAR file into the container
COPY target/*.jar customer-service.jar

# Expose the application's port (default for Spring Boot is 8080)
EXPOSE 8080

# Environment variables (e.g., database connection properties)
# These can also be passed via a Docker Compose file or Kubernetes manifests
ENV SPRING_DATASOURCE_HOST=localhost \
    SPRING_DATASOURCE_USERNAME=root \
    SPRING_DATASOURCE_PASSWORD=admin

# Run the application
ENTRYPOINT ["java", "-jar", "customer-service.jar"]
