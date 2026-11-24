# Loan Approval REST API

![CI](https://github.com/2307shukla/loan-approval-api/actions/workflows/ci.yml/badge.svg)

A Spring Boot REST API that simulates a **loan approval system** with credit scoring, a decision engine, payments and dispute handling. Built as a backend integration service for Pega BPM Customer Service applications.

**Live:** https://loan-approval-api-viia.onrender.com (Swagger UI at `/swagger-ui.html`; the free tier sleeps, so the first request can take a minute)

## Tech stack
Java 21 · Spring Boot 3.4.5 · Spring Security + JWT · Spring Data JPA · PostgreSQL (H2 for local dev) · Bucket4j rate limiting · springdoc OpenAPI · Actuator · Docker · GitHub Actions

## Authentication
Every endpoint except `/api/auth/**`, Swagger and `/actuator/health` needs a bearer token.

1. `POST /api/auth/register` with `{"username": "...", "password": "..."}`
2. `POST /api/auth/login` with the same body returns `{"token": "..."}`
3. Send `Authorization: Bearer <token>` on every other call.

## Endpoints
| Area | Endpoints |
|---|---|
| Auth | `POST /api/auth/register`, `POST /api/auth/login` |
| Credit | `GET /api/credit/score` |
| Customers | `POST /api/customer/create`, `GET /api/customer/{customerId}`, `GET /api/customer/all` |
| Loans | `POST /api/loan/apply`, `GET /api/loan/all`, `GET /api/loan/{id}` |
| Payments | `POST /api/payment/make`, `GET /api/payment/customer/{customerId}`, `GET /api/payment/all`, `GET /api/payment/{id}` |
| Disputes | `POST /api/dispute/raise`, `PUT /api/dispute/resolve/{disputeId}`, `GET /api/dispute/status/{disputeId}`, `GET /api/dispute/all` |
| Ops | `GET /actuator/health`, `GET /actuator/info` |

Full request and response schemas are in Swagger UI.

## Running locally
```bash
export JWT_SECRET="$(openssl rand -base64 48)"
SPRING_PROFILES_ACTIVE=dev ./mvnw spring-boot:run     # in-memory H2, H2 console enabled
```

## Configuration (environment variables)
| Variable | Default | Purpose |
|---|---|---|
| `JWT_SECRET` | *(required, no default)* | Token signing key, at least 32 bytes. The app refuses to start without it. |
| `DATABASE_URL` | `jdbc:h2:mem:loandb` | JDBC URL. Use `jdbc:postgresql://host:5432/db` in production. |
| `DB_USERNAME` / `DB_PASSWORD` | `sa` / empty | Database credentials. |
| `DB_POOL_SIZE` | `5` | Connection pool size. |
| `DDL_AUTO` | `update` | Hibernate schema mode. |
| `JWT_EXPIRATION_MS` | `86400000` | Token lifetime (24 h). |
| `PORT` | `8080` | HTTP port. |
| `SPRING_PROFILES_ACTIVE` | *(none)* | `dev` enables the H2 console and SQL logging. Never use in production. |

## Production deployment
- **Persistent database:** the default H2 database is in-memory and loses all data on restart. Set `DATABASE_URL`, `DB_USERNAME` and `DB_PASSWORD` to a PostgreSQL instance (for example Neon or Render Postgres).
- **Secret:** generate `JWT_SECRET` once and keep it only in the platform's environment settings. Rotating it invalidates all issued tokens.
- **Health check:** point the platform's health check at `/actuator/health`. It is public and exempt from rate limiting.
- **Container:** multi-stage image, runs as a non-root user, with a built-in `HEALTHCHECK` and container-aware JVM memory settings (`JAVA_OPTS` to override).
- **Proxy headers:** `X-Forwarded-*` headers are honoured so rate limiting applies per client IP behind Render's proxy.
- **CI:** every push runs `mvn verify` and builds the Docker image.

## Known limitations
- Open self-registration: anyone can create a `USER` account. Fine for a demo; add an admin-approval or invite flow before real use.
- Request bodies for register/login are not validated yet (empty or duplicate usernames).
- Schema is managed by Hibernate `update`; a migration tool (Flyway) is the next step for real data.

## 🏦 Decision Logic

| Credit Score | Loan Amount | Decision |
|---|---|---|
| >= 700 | <= 10,00,000 | APPROVED |
| >= 600 | <= 5,00,000 | APPROVED |
| >= 600 | > 5,00,000 | PENDING |
| < 600 | Any | REJECTED |

## 🔗 Integration
This API is designed to integrate with Pega Customer Service as a REST connector for real-time credit score evaluation during loan case processing.

## 👨‍💻 Author
Abhijeet Shukla
Pega Certified Senior System Architect (CSSA) | BPM Solutions Architect
- LinkedIn: linkedin.com/in/abhijeet-shukla-as2807
- GitHub: github.com/2307shukla
