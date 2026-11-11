package com.abhijeet.loan_approval_api.service;

import com.abhijeet.loan_approval_api.entity.Dispute;
import com.abhijeet.loan_approval_api.repository.DisputeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisputeService {

    private static final Logger logger = 
        LoggerFactory.getLogger(DisputeService.class);

    @Autowired
    private DisputeRepository disputeRepository;

    public Dispute raiseDispute(String customerId, String reason) {
        logger.info("Raising dispute for customerId: {}", customerId);

        if (customerId == null || customerId.isEmpty()) {
            logger.error("Customer ID is empty!");
            throw new IllegalArgumentException("Customer ID cannot be empty");
        }
        if (reason == null || reason.isEmpty()) {
            logger.error("Reason is empty!");
            throw new IllegalArgumentException("Reason cannot be empty");
        }

        Dispute dispute = new Dispute();
        dispute.setCustomerId(customerId);
        dispute.setReason(reason);
        dispute.setStatus("OPEN");

        logger.info("Dispute raised successfully for customerId: {}", customerId);
        return disputeRepository.save(dispute);
    }

    public Dispute resolveDispute(Long id) {
        logger.info("Resolving dispute with ID: {}", id);
        Dispute dispute = disputeRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Dispute not found with ID: {}", id);
                    return new IllegalArgumentException(
                        "Dispute not found with ID: " + id);
                });
        dispute.setStatus("RESOLVED");
        logger.info("Dispute RESOLVED with ID: {}", id);
        return disputeRepository.save(dispute);
    }

    public Dispute getDisputeStatus(Long id) {
        logger.info("Fetching dispute status for ID: {}", id);
        return disputeRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Dispute not found with ID: {}", id);
                    return new IllegalArgumentException(
                        "Dispute not found with ID: " + id);
                });
    }

    public List<Dispute> getAllDisputes() {
        logger.info("Fetching all disputes");
        return disputeRepository.findAll();
    }
}