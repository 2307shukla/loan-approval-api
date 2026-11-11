package com.abhijeet.loan_approval_api.controller;

import com.abhijeet.loan_approval_api.entity.Customer;
import com.abhijeet.loan_approval_api.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@Tag(name = "Customer Controller", description = "APIs for Customer Management")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Operation(summary = "Create a customer",
               description = "Register a new customer in the system")
    @PostMapping("/create")
    public Customer createCustomer(
            @RequestParam String customerId,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone) {
        return customerService.createCustomer(customerId, name, email, phone);
    }

    @Operation(summary = "Get customer by ID",
               description = "Returns a single customer by customer ID")
    @GetMapping("/{customerId}")
    public Customer getCustomer(@PathVariable String customerId) {
        return customerService.getCustomer(customerId);
    }

    @Operation(summary = "Get all customers",
               description = "Returns list of all registered customers")
    @GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
}