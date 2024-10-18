FROM openjdk:17-jdk-slim

LABEL authors="alvaroarauz"

WORKDIR /app

COPY target/product_rate_manager-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

