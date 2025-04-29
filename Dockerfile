FROM gradle:8.4-jdk21 AS builder
WORKDIR /app
COPY . .
RUN gradle clean build

FROM openjdk:21-slim-buster
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]