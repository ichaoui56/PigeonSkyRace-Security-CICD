# Étape 1 : Construire le projet avec Maven
FROM maven:3.9.9-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2 : Utiliser une image JDK pour exécuter l'application
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copier le fichier JAR généré depuis l'étape de construction
COPY --from=builder /app/target/PigeonSkyRaceSpringSecurity-0.0.1-SNAPSHOT.jar app.jar

# Exposer les ports HTTP (8080) et HTTPS (8443)
EXPOSE 8080
EXPOSE 8443

# Démarrer l'application Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
