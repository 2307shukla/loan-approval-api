package com.abhijeet.loan_approval_api.service;

import com.abhijeet.loan_approval_api.entity.Dispute;
import com.abhijeet.loan_approval_api.repository.DisputeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisputeService {

    @Autowired
    private DisputeRepository disputeRepository;

    public Dispute raiseDispute(String customerId, String reason) {
        if (customerId == null || customerId.isEmpty()) {
            throw new IllegalArgumentException("Customer ID cannot be empty");
        }
        if (reason == null || reason.isEmpty()) {
            throw new IllegalArgumentException("Reason cannot be empty");
        }

        Dispute dispute = new Dispute();
        dispute.setCustomerId(customerId);
        dispute.setReason(reason);
        dispute.setStatus("OPEN");

        return disputeRepository.save(dispute);
    }

    public Dispute resolveDispute(Long id) {
        Dispute dispute = disputeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Dispute not found with ID: " + id));
        dispute.setStatus("RESOLVED");
        return disputeRepository.save(dispute);
    }

    public Dispute getDisputeStatus(Long id) {
        return disputeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Dispute not found with ID: " + id));
    }

    public List<Dispute> getAllDisputes() {
        return disputeRepository.findAll();
    }
}