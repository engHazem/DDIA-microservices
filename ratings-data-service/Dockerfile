FROM maven:3.9-jdk-17
LABEL authors="yousef"
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:resolve
COPY src ./src
EXPOSE 8083
ENTRYPOINT ["top", "-b"]