# ⚙️ BackEndPiDev — *TacticFoot Backend Service*

## 📘 Project Overview

**BackEndPiDev** is the backend component of **TacticFoot**, a football club management platform tailored to support various roles within a sports organization—such as coaches, analysts, doctors, and club administrators.

This RESTful API, built with **Spring Boot**, is responsible for handling all business logic and data management, providing secure and efficient services to the Angular-based frontend (**FrontEndPiDev**).

---

## 🔐 Core Features

- ✅ Full CRUD support for players, clubs, matches, users, and medical records  
- 📅 Scheduling and management of training sessions and matches  
- 🔐 Secure REST API with role-based access control (RBAC)  
- 📊 Scalable architecture ready for integration with analytics and reporting tools  

---

## 👥 Supported User Roles

- 👨‍🏫 **Coaches** – Manage player performance and training sessions  
- 📊 **Analysts** – Track and analyze game data and player stats  
- 🩺 **Doctors** – Manage player health and medical history  
- 🏢 **Club Administrators** – Oversee operations and user roles  
- 🛡️ **Super Administrators** – Platform-wide configuration and security  

---

## 🛠️ Tech Stack

| Technology         | Purpose                             |
|--------------------|--------------------------------------|
| **Spring Boot**    | Main framework for backend logic     |
| **Spring Security**| Authentication and authorization     |
| **Spring Data JPA**| ORM and database interaction         |
| **MySQL**          | Relational database                  |
| **Maven**          | Project build and dependencies       |
| **Lombok**         | Boilerplate code reduction           |
| **ModelMapper**    | DTO-Entity mapping                   |
| **Swagger**        | Interactive API documentation        |

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/ALABENSALEMDBS/BackEndPiDev.git
cd BackEndPiDev
