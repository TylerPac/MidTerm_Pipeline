# Use a lightweight Java runtime
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the built JAR file into the container
COPY target/my-app-1.0-SNAPSHOT.jar app.jar

# Expose port 8080 (if your app runs a web server)
EXPOSE 9090

# Run the application
CMD ["java", "-jar", "app.jar"]
