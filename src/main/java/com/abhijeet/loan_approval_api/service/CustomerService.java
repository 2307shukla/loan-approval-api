package com.abhijeet.loan_approval_api.service;

import com.abhijeet.loan_approval_api.entity.Customer;
import com.abhijeet.loan_approval_api.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private static final Logger logger = 
        LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(String customerId, String name,
                                   String email, String phone) {
        logger.info("Creating customer with customerId: {}", customerId);

        if (customerId == null || customerId.isEmpty()) {
            logger.error("Customer ID is empty!");
            throw new IllegalArgumentException("Customer ID cannot be empty");
        }
        if (name == null || name.isEmpty()) {
            logger.error("Name is empty!");
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (email == null || email.isEmpty()) {
            logger.error("Email is empty!");
            throw new IllegalArgumentException("Email cannot be empty");
        }

        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);

        logger.info("Customer created successfully with customerId: {}", customerId);
        return customerRepository.save(customer);
    }

    public Customer getCustomer(String customerId) {
        logger.info("Fetching customer with customerId: {}", customerId);
        Customer customer = customerRepository.findByCustomerId(customerId);
        if (customer == null) {
            logger.error("Customer not found with ID: {}", customerId);
            throw new IllegalArgumentException(
                "Customer not found with ID: " + customerId);
        }
        return customer;
    }

    public List<Customer> getAllCustomers() {
        logger.info("Fetching all customers");
        return customerRepository.findAll();
    }
}