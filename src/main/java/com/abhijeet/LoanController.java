package com.abhijeet.loan_approval_api;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/loan")
public class LoanController {

    @PostMapping("/apply")
    public Map<String, Object> applyLoan(
            @RequestParam String customerId,
            @RequestParam double loanAmount,
            @RequestParam String purpose) {

        Map<String, Object> response = new HashMap<>();

        // Mock credit score check
        int creditScore;
        if (customerId.startsWith("A")) {
            creditScore = 750;
        } else if (customerId.startsWith("B")) {
            creditScore = 620;
        } else {
            creditScore = 480;
        }

        // Loan decision logic
        String decision;
        String reason;

        if (creditScore >= 700 && loanAmount <= 1000000) {
            decision = "APPROVED";
            reason = "Good credit score and acceptable loan amount";
        } else if (creditScore >= 600 && loanAmount <= 500000) {
            decision = "APPROVED";
            reason = "Acceptable credit score with moderate loan amount";
        } else if (creditScore >= 600 && loanAmount > 500000) {
            decision = "PENDING";
            reason = "Manual review required for high loan amount";
        } else {
            decision = "REJECTED";
            reason = "Credit score below minimum threshold";
        }

        response.put("customerId", customerId);
        response.put("loanAmount", loanAmount);
        response.put("purpose", purpose);
        response.put("creditScore", creditScore);
        response.put("decision", decision);
        response.put("reason", reason);
        response.put("applicationId", "LOAN-" + 
            System.currentTimeMillis());

        return response;
    }

    @GetMapping("/status/{applicationId}")
    public Map<String, Object> getLoanStatus(
            @PathVariable String applicationId) {

        Map<String, Object> response = new HashMap<>();
        response.put("applicationId", applicationId);
        response.put("status", "UNDER_REVIEW");
        response.put("message", 
            "Your application is being processed");
        return response;
    }
}