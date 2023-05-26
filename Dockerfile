#FROM maven:4.0.0-jdk-20 As build
#COPY . .
#RUN mvn mvn clean package -DskipTests
#FROM openjdk:20
#COPY --from=build /target/aggregatebusinesssolutiontaskbackend.jar aggregatebusinesssolutiontaskbackend.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","aggregatebusinesssolutiontaskbackend.jar"]

# Use Maven 3.8.4 with JDK 17 as the build environment
FROM maven:3.8.4-jdk-17 AS build

# Set the working directory
WORKDIR /app

# Copy the application files into the container
COPY . .

# Build the application
RUN mvn clean package -DskipTests

# Create the final image with Java 17
FROM openjdk:17-jdk

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the build environment
COPY --from=build /app/target/aggregatebusinesssolutiontaskbackend.jar /app/aggregatebusinesssolutiontaskbackend.jar

# Expose the application port
EXPOSE 8080

# Define the startup command
CMD ["java", "-jar", "aggregatebusinesssolutiontaskbackend.jar"]
