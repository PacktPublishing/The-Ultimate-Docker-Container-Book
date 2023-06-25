# Build stage 
FROM gradle:jdk17 AS build 
WORKDIR /app 
COPY . /app 
RUN gradle clean build --no-daemon  
 
# Runtime stage 
FROM openjdk:17-oracle 
COPY --from=build /app/build/libs/kotlin-api-0.0.1-SNAPSHOT.jar app.jar 
EXPOSE 8080 
ENTRYPOINT ["java", "-jar", "/app.jar"]