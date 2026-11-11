package com.abhijeet.loan_approval_api.controller;

import com.abhijeet.loan_approval_api.entity.CreditScore;
import com.abhijeet.loan_approval_api.service.CreditScoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/credit")
@Tag(name = "Credit Score Controller", description = "APIs for Credit Score Management")
public class CreditScoreController {

    @Autowired
    private CreditScoreService creditScoreService;

    @Operation(summary = "Get credit score",
               description = "Get credit score and rating for a customer - EXCELLENT, GOOD, AVERAGE, POOR")
    @GetMapping("/score")
    public CreditScore getCreditScore(@RequestParam String customerId) {
        return creditScoreService.getCreditScore(customerId);
    }
}