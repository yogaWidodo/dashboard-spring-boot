# Use a specific Gradle image to build the project
FROM gradle:jdk17-corretto AS build
WORKDIR /app
COPY . /app

# Run the Gradle build with additional logging
RUN gradle build --no-daemon --stacktrace --info

# Use an official OpenJDK runtime as a parent image
FROM openjdk:24-slim-bookworm
WORKDIR /app

# Copy the JAR file with the correct name
COPY --from=build /app/build/libs/dashboardumroh-0.0.1.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Set environment variables (replace with your actual values)
ENV DATABASE_USERNAME=postgres
ENV DATABASE_PASSWORD=postgres
ENV DATABASE_URL=jdbc:postgresql://localhost:5432/dashboard-umroh
ENV JWT_KEY=sdsfasfasfasfasfewfafdsfldsfasdfsafsffffffffffffffffff

# Health check

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]