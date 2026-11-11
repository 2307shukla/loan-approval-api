package com.abhijeet.loan_approval_api.service;

import com.abhijeet.loan_approval_api.entity.Payment;
import com.abhijeet.loan_approval_api.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private static final Logger logger =
        LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment makePayment(String customerId, 
                               Double amount, String paymentType) {
        logger.info("Payment received - customerId: {}, amount: {}", 
                    customerId, amount);

        if (customerId == null || customerId.isEmpty()) {
            logger.error("Customer ID is empty!");
            throw new IllegalArgumentException("Customer ID cannot be empty");
        }
        if (amount == null || amount <= 0) {
            logger.error("Invalid payment amount: {}", amount);
            throw new IllegalArgumentException(
                "Payment amount must be greater than 0");
        }
        if (paymentType == null || paymentType.isEmpty()) {
            logger.error("Payment type is empty!");
            throw new IllegalArgumentException("Payment type cannot be empty");
        }

        Payment payment = new Payment();
        payment.setCustomerId(customerId);
        payment.setAmount(amount);
        payment.setPaymentType(paymentType);
        payment.setStatus("SUCCESS");

        logger.info("Payment SUCCESS for customerId: {}, amount: {}", 
                    customerId, amount);
        return paymentRepository.save(payment);
    }

    public List<Payment> getPaymentsByCustomer(String customerId) {
        logger.info("Fetching payments for customerId: {}", customerId);
        return paymentRepository.findByCustomerId(customerId);
    }

    public List<Payment> getAllPayments() {
        logger.info("Fetching all payments");
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        logger.info("Fetching payment by ID: {}", id);
        return paymentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Payment not found with ID: {}", id);
                    return new IllegalArgumentException(
                        "Payment not found with ID: " + id);
                });
    }
}