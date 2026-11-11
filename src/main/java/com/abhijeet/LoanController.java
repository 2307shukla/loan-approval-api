package com.abhijeet.loan_approval_api.controller;

import com.abhijeet.loan_approval_api.entity.LoanApplication;
import com.abhijeet.loan_approval_api.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/apply")
    public LoanApplication applyLoan(
            @RequestParam String customerId,
            @RequestParam Double loanAmount,
            @RequestParam String purpose) {
        return loanService.applyLoan(customerId, loanAmount, purpose);
    }

    @GetMapping("/all")
    public List<LoanApplication> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/{id}")
    public LoanApplication getLoanById(@PathVariable Long id) {
        return loanService.getLoanById(id);
    }
}