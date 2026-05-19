# Flood Alerts Backend

## Getting Started

### Run the Application

```bash
# Option 1 - Run Main Class
FloodAlertsApplication.java

# Option 2 - Maven
.\mvnw spring-boot:run
```

---

## Prerequisites

- Java 17+
- PostgreSQL 16
- MongoDB
- Maven

---

## Backend Authentication Setup

This project uses:

- Spring Security
- JWT Authentication
- BCrypt Password Encryption

Authentication Flow:

```text
Register/Login
        ↓
Spring Security
        ↓
JWT Token Generated
        ↓
Frontend Stores Token
        ↓
Protected APIs Validate JWT
```

---

## Environment Setup

Rename `.env.sample` to `.env` in the project root and configure values.

Example:

```env
DB_URL=jdbc:postgresql://localhost:5432/floodalerts
DB_USERNAME=postgres
DB_PASSWORD=your_password

MONGO_URI=mongodb://localhost:27017/floodalerts

APP_JWT_SECRET=mysecretkeymysecretkeymysecretkey123456
APP_JWT_EXPIRATION=86400000
```

---

## Database Setup (PostgreSQL)

### 1. Create Database

```sql
CREATE DATABASE floodalerts;
```

---

### 2. Switch Database

```sql
\c floodalerts
```

---

### 3. Create Users Table

```sql
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(50),
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    notifications_enabled BOOLEAN DEFAULT TRUE
);
```

---

## Database Setup (MongoDB)

MongoDB is used for flood reports.

Make sure MongoDB is running locally:

```bash
mongod
```

Database `floodalerts` will be created automatically on first use.

---

## Authentication API Endpoints

### Register User

```http
POST /api/auth/register
```

Example Request:

```json
{
  "name": "example
  ",
  "email": "example
  @gmail.com",
  "phoneNumber": "0771234567",
  "password": "123456"
}
```

---

### Login User

```http
POST /api/auth/login
```

Example Request:

```json
{
  "email": "example
  @gmail.com",
  "password": "123456"
}
```

Example Response:

```json
{
  "token": "jwt_token_here",
  "userId": 1,
  "email": "example
  @gmail.com"
}
```

---

## JWT Authentication

Protected APIs require JWT token in request headers:

```http
Authorization: Bearer your_jwt_token
```

---

## Project Structure

```text
src/
└── main/
    ├── java/
    │   └── Atomic5/demo/
    │       ├── FloodAlertsApplication.java
    │       ├── controller/
    │       ├── service/
    │       ├── model/
    │       ├── repository/
    │       ├── security/
    │       └── dto/
    └── resources/
        └── application.properties
```

---

## Tech Stack

| Layer | Technology |
|---|---|
| Backend | Spring Boot |
| Relational DB | PostgreSQL 16 |
| NoSQL DB | MongoDB |
| Authentication | JWT + Spring Security |
| Password Encryption | BCrypt |
| Build Tool | Maven |

---

## Run Backend

```bash
.\mvnw spring-boot:run
```

Backend runs on:

```text
http://localhost:8080
```