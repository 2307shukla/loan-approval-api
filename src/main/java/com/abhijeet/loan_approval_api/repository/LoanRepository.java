package com.abhijeet.loan_approval_api.repository;

import com.abhijeet.loan_approval_api.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<LoanApplication, Long> {
}