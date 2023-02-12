FROM eclipse-temurin:17-jdk-focal AS build
WORKDIR /app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

CMD ./mvnw spring-boot:run