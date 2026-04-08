# Bank Account Management System

A modern Java application for managing bank accounts, deposits, and withdrawals, built with a focus on clean code, maintainability, and best practices.  
Project designed and implemented entirely by **Thomas Duong**.

---

## 🌟 Features
- Create and manage bank accounts
- Perform deposits and withdrawals with validation
- Track operations with observers
- Exception handling for invalid operations, insufficient funds, and limits
- Fully tested with unit, integration, and end-to-end tests
- CI/CD pipeline for automated builds and tests

---

## 💻 Tech Stack
- **Java 17** – modern features and strong typing
- **Spring Boot** – REST API and dependency injection
- **Maven** – project build, dependency management, and lifecycle
- **Hibernate / JPA** – ORM for database interactions
- **H2 Database** – in-memory database for testing
- **Docker** – for local database containers
- **JUnit + Mockito** – unit and integration testing
- **MockMvc** – controller tests
- **SonarQube** – static code analysis and code quality monitoring

---

## 🧩 Design & Architecture
- **Clean Code** – readable, maintainable, and well-structured
- **SOLID Principles** – Single Responsibility, Open/Closed, Liskov, Interface Segregation, Dependency Inversion
- **Hexagonal / Clean Architecture** – separation of concerns between domain, application, and infrastructure layers, using **Adapters** for ports
- **Observer Pattern** – automatically tracking operations without coupling the service
- **Strategy Pattern** – flexible handling of deposit and withdrawal operations
- **Factory Pattern** – creation of accounts, operations, and strategies
- **Singleton Pattern** – ensures unique instances where necessary

---

## ⚙️ Running the Project

### Prerequisites
- Java 17+
- Maven 3.8+
- Docker / Docker Compose
- PostgreSQL

### 🟢 Option 1 — Run locally (without Docker)
- Start PostgreSQL (local or external)

- Make sure PostgreSQL is running and matches your configuration:

- Spring.datasource.url=jdbc:postgresql://localhost:5432/bank_db
- Run the application
```bash
mvn clean install
mvn spring-boot:run
```

### 🐳 Option 2 — Run with Docker (recommended)

- This will start both the backend and PostgreSQL.


```bash
docker compose up --build
```

- Application will be available at:

- http://localhost:8080

- The application runs on http://localhost:8080

---
### 🐳 Docker Setup (Production by default)

- The project now uses Docker by default with PostgreSQL for both development and production environments.

- Start the PostgreSQL container:

```bash
docker-compose up -d
```
- The application will automatically connect to the Docker PostgreSQL instance.
- No local database setup is required.
- Ensure your application.properties points to the Docker DB connection (default is jdbc:postgresql://localhost:5436/bank_db with username/password postgres).

### 🧪 Testing
- Unit Tests: focus on individual services and controllers
- Integration Tests: test end-to-end flows with the real database context
- E2E Tests: using MockMvc to simulate API calls
  - SQL Container Tests: PostgreSQL containers are used via Testcontainers to test database interactions in a realistic environment

Run all tests:
```bash
mvn test
```

## 📊 Code Quality
- SonarQube ensures code quality, detects bugs, code smells, and maintainability issues
- Strict naming conventions, proper exception handling, and avoiding duplication

---

## 🔗 Endpoints
- `PATCH /api/bank-accounts/{id}/deposit` – deposit money into an account
- `PATCH /api/bank-accounts/{id}/withdraw` – withdraw money from an account
- `POST /api/bankStatements/{idBankAcc}` – create a statement to get all the operations from the last month

---

## 💡 Notes
- All exceptions are handled globally and return meaningful messages in JSON format
- Observer pattern automatically updates the operations history
- Adapters in the hexagonal architecture separate domain logic from external dependencies
- Dependency injection is used extensively for testability and decoupling
- Only consultation and sharing of the project are allowed – no commercial use

---

## 🔧 CI/CD
- Automated builds and tests via GitHub Actions
- Runs unit, integration, and E2E tests on every push
- SonarQube quality gate enforced

---

## 📌 License
MIT License – feel free to use, modify, and share for consultation or educational purposes only.
