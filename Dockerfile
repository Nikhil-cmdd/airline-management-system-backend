# Use a lightweight JRE for smaller images
FROM eclipse-temurin:21-jre-alpine

# ADD the pre-built JAR file
ADD target/Airline-0.0.1-SNAPSHOT.jar Airline-0.0.1-SNAPSHOT.jar

# Expose Porthe application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/Airline-0.0.1-SNAPSHOT.jar"]