# PigeonSkyRace - Sécurité et Déploiement

Ce projet implémente une solution complète pour sécuriser et déployer l'application PigeonSkyRace en utilisant Keycloak pour l'authentification et l'autorisation, SSL/TLS pour sécuriser les communications, ainsi que l'automatisation CI/CD via Jenkins.

## Table des Matières
- [Contexte du Projet](#contexte-du-projet)
- [Objectifs](#objectifs)
  - [Sécurité](#sécurité)
  - [Déploiement](#déploiement)
- [Architecture de la Sécurité](#architecture-de-la-sécurité)
- [CI/CD avec Jenkins](#ci-cd-avec-jenkins)
- [Configuration Docker](#configuration-docker)
- [Mise en Route](#mise-en-route)

## Contexte du Projet
Cette phase se concentre sur l’intégration d’un fournisseur d'identité tiers (Keycloak) pour la gestion de l’authentification et de l’autorisation, la sécurisation des communications via HTTPS avec SSL/TLS, et l’automatisation des pratiques CI/CD avec Jenkins.

## Objectifs

### Sécurité
- Intégrer Keycloak pour gérer les mécanismes d’authentification et d’autorisation.
- Configurer les rôles et permissions dans Keycloak.
- Activer HTTPS avec un certificat SSL/TLS pour chiffrer les communications.
- Mettre en place OAuth 2 pour sécuriser les endpoints REST.
- Protéger les endpoints REST en utilisant Spring Security.
- Configurer CORS pour limiter les origines approuvées.

### Déploiement
- Configurer un serveur Jenkins pour automatiser les étapes du processus CI/CD.
- Mettre en place un pipeline Jenkins capable de :
  - Récupérer le code source depuis un dépôt Git.
  - Compiler et construire le projet.
  - Déployer le projet dans un conteneur Docker après validation manuelle.
  - Lancer les tests unitaires et générer des rapports de couverture.
  - Configurer des notifications pour informer les développeurs des résultats des builds et tests.

## Architecture de la Sécurité
- **Authentification** : Intégration avec Keycloak pour gérer les utilisateurs et les rôles.
- **Chiffrement** : Mise en œuvre de SSL/TLS pour sécuriser les communications.
- **Spring Security** : Utilisé pour sécuriser les endpoints REST et gérer les rôles utilisateurs.
- **OAuth 2** : Implémenté pour la gestion des tokens d’accès.

## CI/CD avec Jenkins
- Configuration d’un pipeline Jenkins pour les étapes CI/CD suivantes :
  - Construction et compilation du projet.
  - Tests unitaires avec rapports de couverture.
  - Création d’images Docker et déploiement.
  - Notifications sur l’état des builds.

## Configuration Docker
Voici la configuration Docker utilisée pour ce projet :

```yaml
version: '3.8'

services:
  postgres:
    image: postgres:latest
    container_name: postgres-container
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: 123
      POSTGRES_DB: PSRDep
    networks:
      - my_network
    ports:
      - "5433:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

  jenkins:
    image: jenkins/jenkins:lts
    container_name: jenkins
    user: root
    environment:
      JAVA_OPTS: "-Djenkins.install.runSetupWizard=false"
    ports:
      - "8080:8080"
      - "50000:50000"
    volumes:
      - jenkins_home:/var/jenkins_home
      - /var/run/docker.sock:/var/run/docker.sock
    networks:
      - my_network

  spring-app:
    build:
      context: .
      dockerfile: Dockerfile
    container_name: pigeonskyracedep
    networks:
      - my_network
    ports:
      - "8443:8443"
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres-container:5432/PSRDep
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: 123
    volumes:
      - keystore_volume:/app
    depends_on:
      - postgres

volumes:
  postgres_data:
  keystore_volume:
  jenkins_home:

networks:
  my_network:
    driver: bridge
```

## Mise en Route

### Prérequis
- JDK 22
- Docker et Docker Compose
- Jenkins installé et configuré

### Installation
1. Clonez le dépôt :
   ```bash
   git clone https://github.com/votreutilisateur/pigeonskyrace-security.git
   cd pigeonskyrace-security
   ```
2. Configurez Keycloak et PostgreSQL à l’aide du fichier `docker-compose.yml`.
3. Configurez le serveur Jenkins et ajoutez le pipeline pour CI/CD.
4. Démarrez l’application Spring Boot en HTTPS :
   ```bash
   mvn install
   mvn spring-boot:run
   ```

### Accédez à l'application
- **HTTPS** : [https://localhost:8443](https://localhost:8443)
- **Jenkins** : [http://localhost:8080](http://localhost:8080)

### Tests
Activez les tests en exécutant :
```bash
mvn test
```

Des rapports de couverture sont générés dans le répertoire `target/site/jacoco`.

