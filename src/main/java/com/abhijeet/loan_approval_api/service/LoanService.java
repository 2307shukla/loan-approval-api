package com.abhijeet.loan_approval_api.service;

import com.abhijeet.loan_approval_api.entity.LoanApplication;
import com.abhijeet.loan_approval_api.repository.LoanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    private static final Logger logger = 
        LoggerFactory.getLogger(LoanService.class);

    @Autowired
    private LoanRepository loanRepository;

    public LoanApplication applyLoan(String customerId, 
                                      Double loanAmount, String purpose) {
        logger.info("Loan application received - customerId: {}, amount: {}", 
                    customerId, loanAmount);

        if (customerId == null || customerId.isEmpty()) {
            logger.error("Customer ID is empty!");
            throw new IllegalArgumentException("Customer ID cannot be empty");
        }
        if (loanAmount == null || loanAmount <= 0) {
            logger.error("Invalid loan amount: {}", loanAmount);
            throw new IllegalArgumentException("Loan amount must be greater than 0");
        }
        if (purpose == null || purpose.isEmpty()) {
            logger.error("Purpose is empty!");
            throw new IllegalArgumentException("Purpose cannot be empty");
        }

        LoanApplication app = new LoanApplication();
        app.setCustomerId(customerId);
        app.setLoanAmount(loanAmount);
        app.setPurpose(purpose);

        if (loanAmount <= 500000) {
            app.setDecision("APPROVED");
            app.setReason("Loan amount is within limit");
            logger.info("Loan APPROVED for customerId: {}", customerId);
        } else {
            app.setDecision("REJECTED");
            app.setReason("Loan amount exceeds limit");
            logger.warn("Loan REJECTED for customerId: {}, amount: {}", 
                        customerId, loanAmount);
        }

        return loanRepository.save(app);
    }

    public List<LoanApplication> getAllLoans() {
        logger.info("Fetching all loan applications");
        return loanRepository.findAll();
    }

    public LoanApplication getLoanById(Long id) {
        logger.info("Fetching loan by ID: {}", id);
        return loanRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Loan not found with ID: {}", id);
                    return new IllegalArgumentException(
                        "Loan not found with ID: " + id);
                });
    }
}