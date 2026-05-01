### Flood Alerts backend
 ## To start run this File : FloodAlertsApplication.java
 ## OR .\mvnw spring-boot:run     


### Creating User Table

## Creat new DB
CREATE DATABASE floodalerts;

## Switch to DB
\c floodalerts

## New Columns
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