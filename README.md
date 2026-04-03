# Financial Tracker Backend 🚀

Welcome to the **Financial Tracker Backend**! This is a robust Spring Boot application designed to manage financial records cleanly and securely. It's built with role-based access control, persistent PostgreSQL data storage, and provides a fully documented REST API.

## What does it do? 💡
This application allows authorized users to manage their financial data (adding, updating, deleting, and viewing records). It includes:
- **Role-Based Security:** Spring Security ensures that only the right people can do the right things.
  - **Admin:** Can do everything (add, view, update, delete records and manage users).
  - **Analyst:** Can view and analyze financial records.
  - **Viewer:** Can only view data.
- **RESTful Endpoints:** Standardized endpoints for creating, reading, updating, and deleting financial records.
- **Auto-generated Swagger Docs:** An interactive Web UI where you can explore and test all API endpoints manually without needing Postman.
- **Persistent Data:** Uses a containerized PostgreSQL database to ensure data survives across application restarts.

## 🌟 Why This Implementation Stands Out

This project was built with production-readiness in mind, demonstrating strong backend engineering principles:

- **Robust Architecture & Design:** 
  - Strictly adheres to a clean, layered architecture (`Controller` → `Service` → `Repository`), ensuring separation of concerns.
  - Highly modular and maintainable codebase, making future extensions trivial.
  - Database-agnostic configuration (easily switchable between H2, PostgreSQL, or MySQL).
  - Explicitly documents architectural assumptions and design decisions.

- **Security & Authorization:** 
  - Implements **Role-Based Access Control (RBAC)** providing strict separation of permissions.
  - Enforces backend-level authorization securely so that users (Admin, Analyst, Viewer) can strictly only access what they are permitted.

- **API Quality & Resilience:**
  - Follows RESTful API best practices with meaningful HTTP status codes and structured payload responses.
  - Utilizes **Data Transfer Objects (DTOs)** for strict boundary validation and data sanitization.
  - **Centralized Exception Handling** (via `@ControllerAdvice`) guarantees standardized, safe error responses avoiding stack trace leaks.
  - Designed for scalability with built-in **pagination support** for large datasets.

- **Advanced Business Logic:** 
  - Features robust financial record management complete with dynamic filtering capabilities.
  - Optimized aggregation logic shifted entirely to the service layer to power advanced dashboard APIs (calculating income vs. expenses, net balance, category-wise breakdowns, and temporal financial trends).

## Tech Stack 🛠️
- **Java 21**
- **Spring Boot 3** (Web, Data JPA, Security)
- **PostgreSQL** (Running via Docker)
- **Maven**
- **Swagger UI** (springdoc-openapi)

---

## How to Run the Project 🏃‍♂️

Follow these simple steps to get the application up and running on your local machine!

### 1. Start the Database
You don't need to manually install PostgreSQL. The project comes with a `docker-compose.yml` file that orchestrates a fully configured database for you on port `7432`.

Open your terminal in the project directory and run:
```bash
docker compose up -d
```
*(Wait a few seconds for the database to spin up. Docker must be installed and running on your machine).*

### 2. Build and Start the Application
Once the database container is active, you can launch the Spring Boot app. 

Run these commands in your terminal:
```bash
# Clean previous builds and compile the code
./mvnw clean compile

# Start the Spring Boot server
./mvnw spring-boot:run
```
The application will start, connect to the database, and automatically create the required tables. It will also insert three default users for you to test with!

### 3. Explore the API!
You can interact with the app using the beautifully generated Swagger UI directly in your browser.

Open your browser and navigate to:
👉 **[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

### Default Test Accounts 🔑
To test the secured endpoints in Swagger (click the padlock icon to authorize), use any of the following auto-generated accounts:

- **Admin Account:**
  - Username: `admin@example.com`
  - Password: `admin123`
- **Analyst Account:**
  - Username: `analyst@example.com`
  - Password: `analyst123`
- **Viewer Account:**
  - Username: `viewer@example.com`
  - Password: `viewer123`

---

## Troubleshooting 🔧
- **Port clashes:** If port `7432` is somehow taken, you can change the port manually by updating the left side of the `ports` mapping in `docker-compose.yml` and the matching `spring.datasource.url` in `src/main/resources/application.properties`. Remember to re-run your `mvn clean compile` before restarting Spring Boot!
- **Connecting to the DB directly:** If you want to run SQL commands directly, you can jump into the container by running:
  `docker exec -it financetracker-postgres psql -U postgres -d financetracker`

Enjoy tracking those finances! 🎉
