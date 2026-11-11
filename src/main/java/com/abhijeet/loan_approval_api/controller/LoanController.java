package com.abhijeet.loan_approval_api.controller;

import com.abhijeet.loan_approval_api.entity.LoanApplication;
import com.abhijeet.loan_approval_api.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan")
@Tag(name = "Loan Controller", description = "APIs for Loan Application Management")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Operation(summary = "Apply for a loan",
               description = "Submit a loan application - returns APPROVED or REJECTED")
    @PostMapping("/apply")
    public LoanApplication applyLoan(
            @RequestParam String customerId,
            @RequestParam Double loanAmount,
            @RequestParam String purpose) {
        return loanService.applyLoan(customerId, loanAmount, purpose);
    }

    @Operation(summary = "Get all loan applications",
               description = "Returns list of all loan applications")
    @GetMapping("/all")
    public List<LoanApplication> getAllLoans() {
        return loanService.getAllLoans();
    }

    @Operation(summary = "Get loan by ID",
               description = "Returns a single loan application by ID")
    @GetMapping("/{id}")
    public LoanApplication getLoanById(@PathVariable Long id) {
        return loanService.getLoanById(id);
    }
}