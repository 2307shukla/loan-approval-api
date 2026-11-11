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
| Spring Data JPA | Database ORM |
| H2 Database | In-memory Database |
| Swagger/OpenAPI | API Documentation |
| Docker | Containerization |
| Render | Cloud Deployment |
| GitHub | Version Control |

---

## 📋 API Endpoints

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

## 🛠️ Run Locally

```bash
# Clone the repo
git clone https://github.com/Shukla9876/loan-approval-api.git

# Navigate to project
cd loan-approval-api

# Run the app
./mvnw spring-boot:run
```

## 🌐 Access Locally
- Swagger UI: http://localhost:8080/swagger-ui.html
- H2 Console: http://localhost:8080/h2-console

---

## 👨‍💻 Developer
**Abhijeet Shukla**
- GitHub: https://github.com/Shukla9876
- LinkedIn: www.linkedin.com/in/abhijeet-shukla-as2807