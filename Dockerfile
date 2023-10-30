FROM maven:3.8.7-openjdk-18-slim AS builder

COPY pom.xml /app/

COPY src /app/src/

WORKDIR /app

RUN mvn package

FROM openjdk:18-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]

