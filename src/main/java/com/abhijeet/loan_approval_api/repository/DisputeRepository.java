package com.abhijeet.loan_approval_api.repository;

import com.abhijeet.loan_approval_api.entity.Dispute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisputeRepository extends JpaRepository<Dispute, Long> {
}