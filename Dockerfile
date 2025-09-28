# ===============================
# 1. Build stage
# ===============================
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy pom.xml and download dependencies first (better caching)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build the JAR
COPY src ./src
RUN mvn clean package -DskipTests

# ===============================
# 2. Runtime stage
# ===============================
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy the JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]