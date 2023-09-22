# Use an official Java runtime as a parent image
FROM maven:3.8.4-openjdk-11-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled Java application JAR file into the container
COPY . /app

# Build your project using Maven (this assumes your project has a Maven build)
RUN mvn clean package

# Define the command to run your application when the container starts
CMD ["java", "-cp", ".:juiceShop-1.0-SNAPSHOT.jar", "org.testng.TestNG", "src/test/xmlResources/loginTest.xml"]
