package com.abhijeet.loan_approval_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "credit_scores")
public class CreditScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerId;
    private Integer score;
    private String rating;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
    public String getRating() { return rating; }
    public void setRating(String rating) { this.rating = rating; }
}