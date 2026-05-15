# Flood Alerts Backend

## Getting Started

**Run the application:**
```bash
# Option 1 - Run main class
FloodAlertsApplication.java

# Option 2 - Maven wrapper
.\mvnw spring-boot:run
```

---

## Prerequisites

- Java 17+
- PostgreSQL 16
- MongoDB
- Maven

---

## Environment Setup

Rename `.env.sample` to `.env` in the project root and fill in the values:


> **Note:** `GOOGLE_CLIENT_ID` and `GOOGLE_CLIENT_SECRET` are required — get them from [Google Cloud Console](https://console.cloud.google.com/).

---

## Database Setup (PostgreSQL)

### 1. Create the Database
```sql
CREATE DATABASE floodalerts;
```

### 2. Switch to the Database
```sql
\c floodalerts
```

### 3. Create the Users Table
```sql
CREATE TABLE users (
    id INTEGER PRIMARY KEY,
    email VARCHAR(255),
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    name VARCHAR(255),
    password VARCHAR(255),
    notifications_enabled BOOLEAN,
    phone_number VARCHAR(50)
);
```

---

## Database Setup (MongoDB)

MongoDB is used for flood reports. Make sure MongoDB is running locally:

The database `floodalerts` will be created automatically on first use.

---

## Project Structure

```
src/
└── main/
    ├── java/
    │   └── Atomic5/demo/
    │       ├── FloodAlertsApplication.java   ← Entry point
    │       ├── controller/
    │       ├── service/
    │       ├── model/
    │       |── repository/
    |       |── security/
    |       └──dto/
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
| Auth | Google OAuth2 + JWT |
| Build | Maven |