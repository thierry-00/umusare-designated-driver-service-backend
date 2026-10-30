# Umusare Designated Driver Service Backend

**Name:** IRADUKUNDA Thierry
**Student ID:** 27428
**Project:** Umusare Designated Driver Service
**Course:** Web Technology and Internet

Umusare sends a professional driver to take a customer home in the customer's own car after a night out in Kigali. This repository holds the backend, built with Spring Boot, Spring Data JPA and PostgreSQL.

## Requirements

- Java 17 or higher
- Maven 3.8 or higher
- PostgreSQL 14 or higher

## Steps to run the API

1. Create the database in PostgreSQL:

   ```
   CREATE DATABASE umusare_db;
   ```

2. Open `src/main/resources/application.properties` and set `spring.datasource.username` and `spring.datasource.password` to your PostgreSQL credentials.

3. Start the application:

   ```
   mvn spring-boot:run
   ```

4. The API runs on `http://localhost:8081`. The tables are created automatically in `umusare_db`.

## Base paths

| Entity | Path |
| --- | --- |
| CarType | /api/car-types |
| Zone | /api/zones |
| Driver | /api/drivers |
| Customer | /api/customers |
| RideRequest | /api/ride-requests |

## Postman

The Postman collection is `umusare-designated-driver-service-midterm.postman_collection.json` in the root of this repository.
