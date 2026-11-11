package com.abhijeet.loan_approval_api.controller;

import com.abhijeet.loan_approval_api.entity.Payment;
import com.abhijeet.loan_approval_api.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
@Tag(name = "Payment Controller", description = "APIs for Payment Management")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Operation(summary = "Make a payment",
               description = "Process a payment for a customer")
    @PostMapping("/make")
    public Payment makePayment(
            @RequestParam String customerId,
            @RequestParam Double amount,
            @RequestParam String paymentType) {
        return paymentService.makePayment(customerId, amount, paymentType);
    }

    @Operation(summary = "Get payments by customer",
               description = "Returns all payments for a specific customer")
    @GetMapping("/customer/{customerId}")
    public List<Payment> getPaymentsByCustomer(
            @PathVariable String customerId) {
        return paymentService.getPaymentsByCustomer(customerId);
    }

    @Operation(summary = "Get all payments",
               description = "Returns list of all payments")
    @GetMapping("/all")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @Operation(summary = "Get payment by ID",
               description = "Returns a single payment by ID")
    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }
}