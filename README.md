# Loan Approval REST API

A Spring Boot REST API that simulates a **Loan Approval System** with credit scoring and decision engine. Built as a backend integration service for Pega BPM Customer Service applications.

## 🚀 Live API
> https://loan-approval-api-viia.onrender.com

## 🛠️ Tech Stack
- Java 21
- Spring Boot 4.1
- Maven
- Docker
- Render (Cloud Deployment)

## 📋 API Endpoints

### 1. Credit Score Check
GET /api/credit/score?customerId={id}

Returns credit score and eligibility for a customer.

Example:
GET https://loan-approval-api-viia.onrender.com/api/credit/score?customerId=A001

Response:
{
  "customerId": "A001",
  "creditScore": 750,
  "status": "EXCELLENT",
  "eligible": true
}

### 2. Loan Application
POST /api/loan/apply?customerId={id}&loanAmount={amount}&purpose={purpose}

Submits a loan application and returns approval decision.

Example:
POST https://loan-approval-api-viia.onrender.com/api/loan/apply?customerId=A001&loanAmount=500000&purpose=HomeLoan

Response:
{
  "customerId": "A001",
  "loanAmount": 500000.0,
  "purpose": "HomeLoan",
  "creditScore": 750,
  "decision": "APPROVED",
  "reason": "Good credit score and acceptable loan amount",
  "applicationId": "LOAN-1794287010638"
}

### 3. Loan Status
GET /api/loan/status/{applicationId}

Returns current status of a loan application.
### 4. Raise Dispute
POST /api/dispute/raise?customerId={id}&applicationId={id}&reason={reason}&description={text}

Raises a dispute for a loan application.

Example reasons: WRONGREJECTION, INTERESTRATE, DOCUMENTISSUE

Response:
{
  "disputeId": "DISP-1794309459542",
  "status": "OPEN",
  "priority": "HIGH",
  "assignedTo": "Senior Credit Manager",
  "message": "Dispute raised successfully. You will hear back within 48 hours."
}

### 5. Dispute Status
GET /api/dispute/status/{disputeId}

Returns current status of a dispute.

### 6. Resolve Dispute
PUT /api/dispute/resolve/{disputeId}?resolution={text}&resolvedBy={name}

Resolves an open dispute.
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
- GitHub: github.com/Shukla9876
