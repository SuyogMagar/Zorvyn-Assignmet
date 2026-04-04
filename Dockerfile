# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Switch to copying the pom.xml and source code directly
# We use the official Maven image to avoid issues with local mvnw scripts 
# (sometimes they don't have execute permissions natively)
COPY pom.xml .
COPY src ./src

# Build the application, skipping tests since database isn't available during build
RUN mvn clean package -DskipTests

# Stage 2: Create the minimal production image
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the built JAR file from the build stage
# Renaming it to app.jar removes the need to know the exact version/filename
COPY --from=build /app/target/*.jar app.jar

# Expose the port (Render handles port binding via the PORT env variable)
ENV PORT=8080
EXPOSE $PORT

# Command to run the application
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT}"]