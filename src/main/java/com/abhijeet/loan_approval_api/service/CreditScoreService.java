package com.abhijeet.loan_approval_api.service;

import com.abhijeet.loan_approval_api.entity.CreditScore;
import com.abhijeet.loan_approval_api.repository.CreditScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditScoreService {

    @Autowired
    private CreditScoreRepository creditScoreRepository;

    public CreditScore getCreditScore(String customerId) {
        if (customerId == null || customerId.isEmpty()) {
            throw new IllegalArgumentException("Customer ID cannot be empty");
        }

        CreditScore creditScore = new CreditScore();
        creditScore.setCustomerId(customerId);

        // Generate score based on customerId
        int score = Math.abs(customerId.hashCode()) % 900 + 100;
        creditScore.setScore(score);

        // Set rating based on score
        if (score >= 750) {
            creditScore.setRating("EXCELLENT");
        } else if (score >= 600) {
            creditScore.setRating("GOOD");
        } else if (score >= 400) {
            creditScore.setRating("AVERAGE");
        } else {
            creditScore.setRating("POOR");
        }

        return creditScoreRepository.save(creditScore);
    }
}