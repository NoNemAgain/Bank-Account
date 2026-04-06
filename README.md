Bank Account Management System

A modern Java application for managing bank accounts, deposits, and withdrawals, built with a focus on clean code, maintainability, and best practices.

🌟 Features
Create and manage bank accounts
Perform deposits and withdrawals with validation
Track operations with observers
Exception handling for invalid operations, insufficient funds, and limits
Fully tested with unit, integration, and end-to-end tests
CI/CD pipeline for automated builds and tests

💻 Tech Stack
Java 17 – modern features and strong typing
Spring Boot – REST API and dependency injection
Maven – project build, dependency management, and lifecycle
Hibernate / JPA – ORM for database interactions
H2 Database – in-memory database for testing
JUnit + Mockito – unit and integration testing
MockMvc – controller tests
SonarQube – static code analysis and code quality monitoring

🧩 Design & Architecture
Clean Code – readable, maintainable, and well-structured code
SOLID Principles – Single Responsibility, Open/Closed, Liskov, Interface Segregation, Dependency Inversion
Hexagonal / Clean Architecture – separation of concerns between domain, application, and infrastructure layers with Adapters for ports
Observer Pattern – tracking operations without coupling the service 
Strategy Pattern – flexible handling of deposit and withdrawal operations
Factory Pattern – creation of accounts, operations, and strategies
Singleton Pattern – ensures unique instances where necessary

⚙️ Running the Project
Prerequisites
Java 17+
Maven 3.8+
(Optional) Docker for local DB or H2 embedded
Build & Run
mvn clean install
mvn spring-boot:run

The application runs on http://localhost:8080.

🧪 Testing
Unit Tests: focus on individual services and controllers
Integration Tests: test end-to-end flows with the real database context
E2E Tests: using MockMvc to simulate API calls

Run tests with: mvn test

📊 Code Quality
SonarQube ensures code quality, detects bugs, code smells, and maintainability issues
The project follows strict naming conventions, proper exception handling, and avoids duplication

🔗 Endpoints
PATCH /api/bank-accounts/{id}/deposit – deposit money into an account
PATCH /api/bank-accounts/{id}/withdraw – withdraw money from an account

💡 Notes
All exceptions are handled globally and return meaningful messages in JSON format
Observer pattern is implemented to automatically update the operations history
Adapters in the hexagonal architecture separate domain logic from external dependencies
Dependency injection is used extensively for testability and decoupling
Only consultation and sharing of the project are allowed – no commercial use
Project designed and implemented entirely by Thomas Duong

📌 License

MIT License – feel free to use, modify, and share for consultation or educational purposes only.
