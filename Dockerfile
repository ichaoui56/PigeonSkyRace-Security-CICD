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
COPY --from=builder /app/target/*.jar /app/ROOT.jar

# Exposer les ports HTTPS (8443)
EXPOSE 8443

# Démarrer l'application Spring Boot
ENTRYPOINT ["java", "-jar", "ROOT.jar"]
