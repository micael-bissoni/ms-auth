# Trevvo Auth Microservice

A professional-grade authentication and authorization microservice built with **Java Spring Boot 4**, designed for scalability, security, and maintainability. This project serves as a reference implementation for modern distributed systems, leveraging **JWT (JSON Web Tokens)** for stateless authentication and **Clean Architecture** principles.

## 🚀 Key Features

- **Stateless Authentication**: Fully implemented using JWT with customizable expiration and secret management.
- **RESTful API**: Secure endpoints for user registration, login, and profile management.
- **Security**: Robust integration with **Spring Security**, featuring customized filters and password encoding using BCrypt.
- **Clean Architecture & DDD**: Designed with Domain-Driven Design principles to ensure low coupling and high cohesion.
- **LENIENT JSON Support**: Custom Jackson configuration to handle various client payload formats safely.
- **CORS Configuration**: Pre-configured for seamless integration with modern frontend frameworks (supported for `http://localhost:4200`).

## 🛠 Tech Stack

- **Core**: Java 21+
- **Framework**: Spring Boot 4
- **Security**: Spring Security, Java JWT (Auth0)
- **Data**: Spring Data JPA, H2 Database (In-Memory)
- **Utilities**: Lombok, Jackson (Streaming/Databind)
- **Build Tool**: Maven

## 🏗 Architecture & Design Patterns

The project follows strict software engineering standards to ensure high-quality delivery:

- **Monorepo Structure**: Centralized management for multi-service environments.
- **TDD (Test-Driven Development)**: Comprehensive test suites ensuring system integrity.
- **DDD (Domain-Driven Design)**: Business logic is decoupled from infrastructure and delivery mechanisms.
- **Clean Code**: Adherence to SOLID principles and readability best practices.
- **Atomic Design**: (Where applicable) Consistent structural patterns across the codebase.

## 🏁 Getting Started

### Prerequisites

- Java 21 or higher
- Maven (or use the provided `./mvnw` wrapper)

### Running the Application

1. Clone the repository.
2. Navigate to the project root:
   ```bash
   cd auth
   ```
3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
The server will start on `http://localhost:8080`.

### Running Tests

Execute the test suite to verify the application's integrity:

```bash
./mvnw clean test
```

---

*This project is part of the Trevvo ecosystem, focused on delivering high-performance microservices architecture.*