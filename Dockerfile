# Start with a base image
FROM openjdk:17-jdk-alpine

# Set the working directory to /app
WORKDIR /app

# Copy the application jar file to the container
COPY target/Test_Gpt-0.0.1-SNAPSHOT.jar /app/Test_Gpt.jar

# Expose port 8080
EXPOSE 8080

# Start the application
ENTRYPOINT ["java","-jar","Test_Gpt.jar"]
