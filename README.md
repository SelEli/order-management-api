# Backend de gestion de commandes — API Spring Boot 3

Projet réalisé en autonomie complète.  
Objectif : concevoir une API backend structurée en couches (Controller / Service / Repository) avec authentification JWT, rôles, CRUD produits et commandes, validation et documentation Swagger.

---

## 📚 Table des matières
- [Objectifs du projet](#-objectifs-du-projet)
- [Ce que j’ai appris](#-ce-que-jai-appris)
- [Architecture technique](#-architecture-technique)
- [Stack technique](#-stack-technique)
- [Sécurité](#-sécurité)
- [Structure du projet](#-structure-du-projet)
- [Fonctionnalités principales](#-fonctionnalités-principales)
- [Qualité et documentation](#-qualité-et-documentation)

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
