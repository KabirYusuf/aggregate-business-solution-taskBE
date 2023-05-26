FROM maven:4.0.0-jdk-20 As build
COPY . .
RUN mvn mvn clean package -DskipTests
FROM openjdk:20
COPY --from=build /target/aggregatebusinesssolutiontaskbackend.jar aggregatebusinesssolutiontaskbackend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","aggregatebusinesssolutiontaskbackend.jar"]