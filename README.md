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
  <img alt="License" src="https://img.shields.io/badge/License-MIT-blue?style=flat-square">
</p>

---

## ✨ Overview

**Banking API** is a backend service that models the core domain of a bank: **users** own
**accounts**, and accounts exchange money through **transactions** (deposits, withdrawals, and
transfers). The codebase follows a clean, layered architecture (Controller → Service → Repository)
with DTO-based boundaries and BCrypt-secured credentials. The full REST surface is exposed and
documented interactively via **Swagger UI / OpenAPI**.

---

## 🚀 Features

- 👤 **User management** — create, fetch, list, and delete users
- 💳 **Account management** — checking & savings accounts linked to users
- 💸 **Transactions** — deposits, withdrawals, and account-to-account transfers
- 🔐 **Security** — passwords hashed with BCrypt via Spring Security
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

- **User** — `firstName`, `lastName`, `email`, `password`, and a list of accounts
- **Account** — `accountNumber`, `balance`, `accountType` (`CHECKING` | `SAVINGS`), owned by a user
- **Transaction** — `amount`, `dateTime`, `transactionType` (`DEPOSIT` | `WITHDRAWAL` | `TRANSFER`),
  with source and destination accounts

---

## 📂 Project Structure

```
src/main/java/com/banking/api
├── BankingApiApplication.java     # Application entry point
├── config/                        # Spring configuration (security, beans)
├── controller/                    # REST controllers (users, accounts, transactions)
├── model/                         # JPA entities & enums
├── repository/                    # Spring Data JPA repositories
├── service/                       # Service interfaces
│   └── impl/                      # Service implementations
└── dto/                           # Request/response DTOs (user, account, transaction)
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

## 📡 API Endpoints

| Method   | Endpoint                            | Description                    |
| -------- | ----------------------------------- | ------------------------------ |
| `POST`   | `/api/users`                        | Create a user                  |
| `GET`    | `/api/users`                        | List all users                 |
| `GET`    | `/api/users/{id}`                   | Get a user by ID               |
| `DELETE` | `/api/users/{id}`                   | Delete a user                  |
| `POST`   | `/api/accounts`                     | Open an account                |
| `GET`    | `/api/accounts/{id}`                | Get an account by ID           |
| `GET`    | `/api/accounts/user/{id}`           | List a user's accounts         |
| `DELETE` | `/api/accounts/{id}`                | Close an account               |
| `POST`   | `/api/transactions`                 | Create a transaction           |
| `GET`    | `/api/transactions/{id}`            | Get a transaction by ID        |
| `GET`    | `/api/transactions/account/{id}`    | List an account's transactions |

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
