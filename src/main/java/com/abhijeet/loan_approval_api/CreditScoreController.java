package com.abhijeet.loan_approval_api.controller;

import com.abhijeet.loan_approval_api.entity.CreditScore;
import com.abhijeet.loan_approval_api.service.CreditScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/credit")
public class CreditScoreController {

    @Autowired
    private CreditScoreService creditScoreService;

    @GetMapping("/score")
    public CreditScore getCreditScore(@RequestParam String customerId) {
        return creditScoreService.getCreditScore(customerId);
    }
}