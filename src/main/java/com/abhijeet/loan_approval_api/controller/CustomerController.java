package com.abhijeet.loan_approval_api.controller;

import com.abhijeet.loan_approval_api.entity.Customer;
import com.abhijeet.loan_approval_api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public Customer createCustomer(
            @RequestParam String customerId,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone) {
        return customerService.createCustomer(customerId, name, email, phone);
    }

    @GetMapping("/{customerId}")
    public Customer getCustomer(@PathVariable String customerId) {
        return customerService.getCustomer(customerId);
    }

    @GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
}