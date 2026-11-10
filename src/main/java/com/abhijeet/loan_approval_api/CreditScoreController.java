package com.abhijeet.loan_approval_api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/credit")
public class CreditScoreController {

    @GetMapping("/score")
    public Map<String, Object> getCreditScore(
            @RequestParam String customerId) {

        Map<String, Object> response = new HashMap<>();

        // Mock credit score logic
        int score;
        String status;

        if (customerId.startsWith("A")) {
            score = 750;
            status = "EXCELLENT";
        } else if (customerId.startsWith("B")) {
            score = 620;
            status = "GOOD";
        } else {
            score = 480;
            status = "POOR";
        }

        response.put("customerId", customerId);
        response.put("creditScore", score);
        response.put("status", status);
        response.put("eligible", score >= 600);

        return response;
    }
}