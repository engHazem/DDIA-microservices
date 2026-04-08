FROM maven:3.9.9-eclipse-temurin-17

LABEL authors="yousef"

WORKDIR /app

# Copy pom.xml first for dependency caching
COPY pom.xml .
RUN mvn dependency:resolve

# Copy source code
COPY src ./src

# Expose your app port
EXPOSE 8082

# Run Spring Boot app
CMD ["mvn", "spring-boot:run"]