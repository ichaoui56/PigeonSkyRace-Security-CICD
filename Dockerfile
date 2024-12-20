# Étape 1 : Construire le projet avec Maven
FROM maven:3.9.9-eclipse-temurin-22 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2 : Utiliser une image JDK pour exécuter l'application
FROM openjdk:22-jdk-slim
WORKDIR /app

# Copier le fichier JAR généré depuis l'étape de construction
COPY --from=builder /app/target/PigeonSkyRace-Security-CICD-0.0.1-SNAPSHOT.jar app.jar

# Copier le keystore dans l'image Docker
COPY src/main/resources/keystore.p12 keystore.p12

# Exposer les ports HTTPS (8443)
EXPOSE 8443

# Démarrer l'application Spring Boot
ENTRYPOINT ["java", "-jar", "ROOT.jar"]
