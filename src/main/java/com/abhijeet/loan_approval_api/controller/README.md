# 🏦 Loan Approval REST API

A production-ready REST API built with Spring Boot for managing loan applications, disputes, payments, and credit scores.

## 🌐 Live Demo
- **Swagger UI:** https://loan-approval-api-1-ysb2.onrender.com/swagger-ui.html
- **Base URL:** https://loan-approval-api-1-ysb2.onrender.com

> ⏱️ Note: Free tier — allow 1-2 mins to wake up on first request

---

## 🚀 Tech Stack
| Technology | Usage |
|---|---|
| Java 21 | Programming Language |
| Spring Boot 3.4.5 | Framework |
| Spring Security + JWT | Authentication |
| Spring Data JPA | Database ORM |
| H2 Database | In-memory Database |
| Bucket4j | Rate Limiting |
| JUnit + Mockito | Unit Testing |
| Swagger/OpenAPI | API Documentation |
| Docker | Containerization |
| Render | Cloud Deployment |
| GitHub | Version Control |

---

## 🔒 Security Features
- JWT Authentication — secure token-based login
- Rate Limiting — max 10 requests per minute per IP
- Input Validation — request data validated automatically
- Exception Handling — proper error responses

---

## 📋 API Endpoints

### 🔐 Auth APIs
| Method | Endpoint | Description |
|---|---|---|
| POST | /api/auth/register | Register new user |
| POST | /api/auth/login | Login and get JWT token |

### 🏦 Loan APIs
| Method | Endpoint | Description |
|---|---|---|
| POST | /api/loan/apply | Apply for a loan |
| GET | /api/loan/all | Get all loans |
| GET | /api/loan/{id} | Get loan by ID |

### ⚖️ Dispute APIs
| Method | Endpoint | Description |
|---|---|---|
| POST | /api/dispute/raise | Raise a dispute |
| PUT | /api/dispute/resolve/{id} | Resolve a dispute |
| GET | /api/dispute/status/{id} | Get dispute status |
| GET | /api/dispute/all | Get all disputes |

### 💳 Payment APIs
| Method | Endpoint | Description |
|---|---|---|
| POST | /api/payment/make | Make a payment |
| GET | /api/payment/all | Get all payments |
| GET | /api/payment/{id} | Get payment by ID |
| GET | /api/payment/customer/{id} | Get payments by customer |

### 👤 Customer APIs
| Method | Endpoint | Description |
|---|---|---|
| POST | /api/customer/create | Create a customer |
| GET | /api/customer/all | Get all customers |
| GET | /api/customer/{id} | Get customer by ID |

### 📊 Credit Score APIs
| Method | Endpoint | Description |
|---|---|---|
| GET | /api/credit/score | Get credit score |

---

## 💡 Loan Decision Logic
| Loan Amount | Decision |
|---|---|
| ≤ 500,000 | ✅ APPROVED |
| > 500,000 | ❌ REJECTED |

## 📊 Credit Score Rating
| Score | Rating |
|---|---|
| 750 - 999 | EXCELLENT |
| 600 - 749 | GOOD |
| 400 - 599 | AVERAGE |
| 100 - 399 | POOR |

---

## 🔑 How to Use API

### Step 1 — Register:
```json
POST /api/auth/register
{
  "username": "yourname",
  "password": "yourpassword"
}
```

### Step 2 — Login:
```json
POST /api/auth/login
{
  "username": "yourname",
  "password": "yourpassword"
}
```

### Step 3 — Use token: