# 1. Build stage: Maven kullanarak JAR üret
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Proje dizini oluştur
COPY . /app
WORKDIR /app



# Projeyi JAR olarak derle
RUN mvn clean package -DskipTests

# 2. Run stage: Sadece derlenmiş JAR dosyasıyla çalışan hafif bir imaj
FROM eclipse-temurin:17-jdk-alpine



# JAR dosyasını kopyala (target klasöründen)
COPY --from=build /app/target/*.jar app.jar

# Spring Boot uygulamasını çalıştır
ENTRYPOINT ["java", "-jar", "app.jar"]
