package com.abhijeet.loan_approval_api.service;

import com.abhijeet.loan_approval_api.entity.LoanApplication;
import com.abhijeet.loan_approval_api.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public LoanApplication applyLoan(String customerId, Double loanAmount, String purpose) {

        if (customerId == null || customerId.isEmpty()) {
            throw new IllegalArgumentException("Customer ID cannot be empty");
        }
        if (loanAmount == null || loanAmount <= 0) {
            throw new IllegalArgumentException("Loan amount must be greater than 0");
        }
        if (purpose == null || purpose.isEmpty()) {
            throw new IllegalArgumentException("Purpose cannot be empty");
        }

        LoanApplication app = new LoanApplication();
        app.setCustomerId(customerId);
        app.setLoanAmount(loanAmount);
        app.setPurpose(purpose);

        if (loanAmount <= 500000) {
            app.setDecision("APPROVED");
            app.setReason("Loan amount is within limit");
        } else {
            app.setDecision("REJECTED");
            app.setReason("Loan amount exceeds limit");
        }

        return loanRepository.save(app);
    }

    public List<LoanApplication> getAllLoans() {
        return loanRepository.findAll();
    }

    public LoanApplication getLoanById(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found with ID: " + id));
    }
}