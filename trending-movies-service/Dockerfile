FROM maven:3.9.9-eclipse-temurin-17

LABEL authors="yousef"

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:resolve

# Copy source code
COPY src ./src

EXPOSE 9091

CMD ["mvn", "spring-boot:run"]