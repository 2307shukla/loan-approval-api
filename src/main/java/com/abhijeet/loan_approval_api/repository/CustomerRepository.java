package com.abhijeet.loan_approval_api.repository;

import com.abhijeet.loan_approval_api.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByCustomerId(String customerId);
}