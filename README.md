<h1 align="center">🏦 Banking API</h1>

<p align="center">
  A clean, layered <strong>RESTful banking service</strong> built with Spring Boot 4 & Java 21 —
  managing users, accounts, and money transfers with secure password handling.
</p>

<p align="center">
  <img alt="Java" src="https://img.shields.io/badge/Java-21-007396?style=flat-square&logo=openjdk&logoColor=white">
  <img alt="Spring Boot" src="https://img.shields.io/badge/Spring%20Boot-4.1.0-6DB33F?style=flat-square&logo=springboot&logoColor=white">
  <img alt="Maven" src="https://img.shields.io/badge/Maven-build-C71A36?style=flat-square&logo=apachemaven&logoColor=white">
  <img alt="H2" src="https://img.shields.io/badge/Database-H2-09476B?style=flat-square&logo=h2database&logoColor=white">
  <img alt="JWT" src="https://img.shields.io/badge/Auth-JWT-000000?style=flat-square&logo=jsonwebtokens&logoColor=white">
  <img alt="License" src="https://img.shields.io/badge/License-MIT-blue?style=flat-square">
</p>

---

## ✨ Overview

**Banking API** is a backend service that models the core domain of a bank: **users** own
**accounts**, and accounts exchange money through **transactions** (deposits, withdrawals, and
transfers). The codebase follows a clean, layered architecture (Controller → Service → Repository)
with DTO-based boundaries and BCrypt-secured credentials. Authentication is handled with
**stateless JWT tokens** and **role-based access control** (USER / ADMIN), and the full REST
surface is documented interactively via **Swagger UI / OpenAPI** — complete with a built-in
*Authorize* button for testing secured endpoints.

---

## 🚀 Features

- 👤 **User management** — register, fetch, list, and delete users
- 💳 **Account management** — checking & savings accounts linked to users
- 💸 **Transactions** — deposits, withdrawals, and account-to-account transfers
- 🔑 **JWT authentication** — stateless register/login issuing signed Bearer tokens (24h expiry)
- 🛡️ **Role-based access control** — `USER` / `ADMIN` roles enforced per endpoint
- 🙋 **Ownership checks** — users can only access their own accounts
- 🔐 **Security** — passwords hashed with BCrypt, endpoints guarded by a JWT filter
- 🧯 **Consistent error handling** — global `@RestControllerAdvice` with structured JSON errors
- 📖 **Interactive API docs** — Swagger UI powered by springdoc OpenAPI
- 🧱 **Clean architecture** — clear separation between web, service, and data layers
- 📦 **DTO-driven API** — request/response models decoupled from JPA entities
- ✅ **Validation** — request payloads validated with Jakarta Bean Validation
- 🗄️ **In-memory H2** — zero-setup database with web console for quick exploration

---

## 🛠️ Tech Stack

| Layer            | Technology                                   |
| ---------------- | -------------------------------------------- |
| Language         | Java 21                                       |
| Framework        | Spring Boot 4.1.0 (Web MVC, Data JPA, Security) |
| Authentication   | JWT (jjwt 0.13) + Spring Security               |
| Persistence      | Spring Data JPA + Hibernate                    |
| Database         | H2 (in-memory, with H2 console)               |
| API docs         | springdoc OpenAPI + Swagger UI                 |
| Validation       | Jakarta Bean Validation                        |
| Boilerplate      | Project Lombok                                 |
| Build            | Maven (with wrapper)                           |

---

## 🧩 Domain Model

```
User ──1───*── Account ──*───1── Transaction
                            (fromAccount / toAccount)
```

- **User** — `firstName`, `lastName`, `email`, `password`, `role` (`USER` | `ADMIN`), and a list of accounts
- **Account** — `accountNumber`, `balance`, `accountType` (`CHECKING` | `SAVINGS`), owned by a user
- **Transaction** — `amount`, `dateTime`, `transactionType` (`DEPOSIT` | `WITHDRAWAL` | `TRANSFER`),
  with source and destination accounts

---

## 📂 Project Structure

```
src/main/java/com/banking/api
├── BankingApiApplication.java     # Application entry point
├── config/                        # Spring configuration (security, OpenAPI, beans)
├── controller/                    # REST controllers (auth, users, accounts, transactions)
├── security/                      # JWT service, auth filter & user details
├── exception/                     # Custom exceptions & global handler
├── model/                         # JPA entities & enums
├── repository/                    # Spring Data JPA repositories
├── service/                       # Service interfaces
│   └── impl/                      # Service implementations
└── dto/                           # Request/response DTOs (auth, user, account, transaction, error)
```

---

## ⚡ Getting Started

### Prerequisites

- **JDK 21+**
- **Maven** (or use the bundled `./mvnw` wrapper — no install needed)

### Run the application

```bash
# Clone the repository
git clone https://github.com/maxim-babets/banking-api.git
cd banking-api

# Run with the Maven wrapper
./mvnw spring-boot:run
```

The API starts on **`http://localhost:8080`**.

### Run the tests

```bash
./mvnw test
```

### Build a runnable JAR

```bash
./mvnw clean package
java -jar target/banking-api-0.0.1-SNAPSHOT.jar
```

---

## 📖 API Documentation (Swagger)

Once the app is running, explore and try every endpoint interactively:

```
Swagger UI:  http://localhost:8080/swagger-ui.html
OpenAPI doc: http://localhost:8080/v3/api-docs
```

---

## 🗄️ H2 Console

While the app is running, browse the in-memory database at:

```
http://localhost:8080/h2-console
```

---

## 🔑 Authentication

Register (or log in) to receive a signed JWT, then send it as a **Bearer token** on every
protected request. Only `/api/auth/**`, Swagger, and the H2 console are public — everything
else requires a valid token, and `GET /api/users` additionally requires the `ADMIN` role.

```bash
# 1. Register a new user
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"firstName": "John", "lastName": "Doe", "email": "john@example.com", "password": "secret"}'

# 2. Log in and get a token
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email": "john@example.com", "password": "secret"}'
```

```jsonc
// Login response
{
  "userId": 1,
  "email": "john@example.com",
  "message": "Login successful",
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

```bash
# 3. Use the token on protected requests
curl http://localhost:8080/api/accounts/1 \
  -H "Authorization: Bearer <token>"
```

> Tokens are HMAC-SHA256 signed and valid for **24 hours**. In Swagger UI, click **Authorize**
> and paste the token to try secured endpoints straight from the browser.

---

## 📡 API Endpoints

| Method   | Endpoint                            | Description                    | Access    |
| -------- | ----------------------------------- | ------------------------------ | --------- |
| `POST`   | `/api/auth/register`                | Register a new user            | 🌐 Public |
| `POST`   | `/api/auth/login`                   | Authenticate & receive a JWT   | 🌐 Public |
| `GET`    | `/api/users`                        | List all users                 | 🛡️ ADMIN  |
| `GET`    | `/api/users/{id}`                   | Get a user by ID               | 🔒 Token  |
| `DELETE` | `/api/users/{id}`                   | Delete a user                  | 🔒 Token  |
| `POST`   | `/api/accounts`                     | Open an account                | 🔒 Token  |
| `GET`    | `/api/accounts/{id}`                | Get an account by ID           | 🔒 Token  |
| `GET`    | `/api/accounts/user/{id}`           | List a user's accounts         | 🔒 Token  |
| `DELETE` | `/api/accounts/{id}`                | Close an account               | 🔒 Token  |
| `POST`   | `/api/transactions`                 | Create a transaction           | 🔒 Token  |
| `GET`    | `/api/transactions/{id}`            | Get a transaction by ID        | 🔒 Token  |
| `GET`    | `/api/transactions/account/{id}`    | List an account's transactions | 🔒 Token  |

> 🌐 Public · 🔒 requires a valid JWT · 🛡️ requires the `ADMIN` role
>
> 🙋 Account endpoints are **ownership-scoped** — a user may only read their own accounts.

---

## 🧯 Error Handling

All errors are funneled through a global `@RestControllerAdvice` and returned as a consistent
JSON envelope, so clients always get the same predictable shape:

```jsonc
{
  "timestamp": "2026-07-13T10:15:30",
  "error": "Not Found",
  "message": "Account not found with id: 42"
}
```

| Exception                       | HTTP status         |
| ------------------------------- | ------------------- |
| `ResourceNotFoundException`     | `404 Not Found`     |
| `AccountAccessDeniedException`  | `404 Not Found`     |
| `BankingApiException` (base)    | `400 Bad Request`   |

---

## 🤝 Contributing

Contributions are welcome! Feel free to open an issue or submit a pull request.

1. Fork the project
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

## 📄 License

Distributed under the **MIT License**. See `LICENSE` for more information.
