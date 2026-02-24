# Backend de gestion de commandes — API Spring Boot 3

Projet réalisé en autonomie complète.  
Objectif : concevoir une API backend structurée en couches (Controller / Service / Repository) avec authentification JWT, rôles, CRUD produits et commandes, validation et documentation Swagger.

---

## 📚 Table des matières
- [Objectifs du projet](#-objectifs-du-projet)
- [Ce que j’ai appris](#-ce-que-jai-appris)
- [Architecture technique](#-architecture-technique)
- [Schéma d’ensemble](#-schéma-densemble)
- [Stack technique](#-stack-technique)
- [Sécurité](#-sécurité)
- [Structure du projet](#-structure-du-projet)
- [Fonctionnalités principales](#-fonctionnalités-principales)
- [Qualité et documentation](#-qualité-et-documentation)
- [Tests](#-tests)


---

## 🎯 Objectifs du projet
- Implémenter une API REST sécurisée avec Spring Security + JWT.  
- Gérer l’authentification (login/register) et les rôles (USER / ADMIN).  
- Structurer le code en couches claires : Controller / Service / Repository.  
- Implémenter les CRUD produits et commandes.  
- Documenter l’API via Swagger.  
- Persister les données via JPA/Hibernate + PostgreSQL.

---

## 🎓 Ce que j’ai appris
- Authentification JWT (génération et validation de tokens).  
- Filtre de sécurité pour valider les requêtes entrantes.  
- Gestion des rôles et protection des endpoints.  
- Architecture en couches propre et maintenable.  
- Manipulation de JPA/Hibernate pour la persistance.  
- Documentation automatique avec Swagger.

---

## 🏗 Architecture technique

| Couche        | Rôle                                      |
|---------------|--------------------------------------------|
| Controller    | Exposition des endpoints REST              |
| Service       | Logique métier                             |
| Repository    | Accès aux données via JPA/Hibernate        |
| Security      | JWT, filtres, configuration des rôles      |

---

## 🗺 Schéma d’ensemble

```
    Client/UI
        │
        ▼
[ Security Layer ]
(JWT Filter → JwtService → SecurityConfig)
        │
        ▼
[ Controller Layer ]
(AuthController, ProductController, OrderController)
        │
        ▼
[ Service Layer ]
(AuthService, ProductService, OrderService)
        │
        ▼
[ Mapper Layer ]
(DTO ↔ Entity)
        │
        ▼
[ Repository Layer ]
(UserRepository, ProductRepository, OrderRepository)
        │
        ▼
PostgreSQL Database
```

---

## 🧰 Stack technique

| Module / Outil | Usage                                      |
|----------------|--------------------------------------------|
| Java 21        | Langage principal                          |
| Spring Boot 3  | Framework backend                          |
| Spring Security| Authentification et autorisation           |
| JPA/Hibernate  | ORM et persistance                         |
| PostgreSQL     | Base de données principale                 |
| Swagger        | Documentation automatique                  |

---

## 🔐 Sécurité
- Authentification JWT (login/register).  
- Filtre de validation des tokens.  
- Protection des endpoints selon les rôles.  
- Hash des mots de passe.  

---

## 📁 Structure du projet

```
src/
├── main/
│   ├── java/
│   │   └── ... (controllers, services, repositories, security)
│   └── resources/
│       ├── application.properties
│       └── schema.sql
└── test/
    └── ...
```

---

## ⚙ Fonctionnalités principales
- Inscription et connexion utilisateur.  
- Génération et validation de tokens JWT.  
- Rôles USER / ADMIN.  
- CRUD produits.  
- CRUD commandes.  
- Gestion des erreurs.  

---

## 📘 Qualité et documentation
- API documentée via Swagger.  
- Modèles persistés via JPA.  

---

## 🧪 Tests

Le projet inclut une base solide de tests automatisés afin de garantir la fiabilité et la maintenabilité du code.

### ✔ Tests unitaires
Les services principaux sont testés avec **JUnit 5** et **Mockito**, en isolation complète :

- `AuthServiceTest` : validation des scénarios d’inscription et de connexion, gestion des erreurs.
- `ProductServiceTest` : création et récupération de produits, gestion des erreurs.
- `OrderServiceTest` : création de commande et mapping des items.

Ces tests vérifient la logique métier sans charger le contexte Spring, pour des exécutions rapides et ciblées.

### ✔ Test d’intégration
Un test d’intégration avec **SpringBootTest** + **H2** valide le bon fonctionnement de la couche repository :

- `ProductIntegrationTest` : sauvegarde et récupération d’un produit via JPA/Hibernate.

Ce test démarre un contexte Spring complet et utilise une base en mémoire pour simuler un environnement réel.

### ✔ Organisation des tests

```
src/test/java/com/ordermanagement/api/
├── service/
│   ├── AuthServiceTest.java
│   ├── ProductServiceTest.java
│   └── OrderServiceTest.java
└── integration/
    └── ProductIntegrationTest.java
```

Cette structure distingue clairement les tests unitaires des tests d’intégration, conformément aux bonnes pratiques professionnelles.

---
