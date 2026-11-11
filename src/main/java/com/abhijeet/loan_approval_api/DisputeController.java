package com.abhijeet.loan_approval_api.controller;

import com.abhijeet.loan_approval_api.entity.Dispute;
import com.abhijeet.loan_approval_api.service.DisputeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dispute")
public class DisputeController {

    @Autowired
    private DisputeService disputeService;

    @PostMapping("/raise")
    public Dispute raiseDispute(
            @RequestParam String customerId,
            @RequestParam String reason) {
        return disputeService.raiseDispute(customerId, reason);
    }

    @PutMapping("/resolve/{disputeId}")
    public Dispute resolveDispute(@PathVariable Long disputeId) {
        return disputeService.resolveDispute(disputeId);
    }

    @GetMapping("/status/{disputeId}")
    public Dispute getDisputeStatus(@PathVariable Long disputeId) {
        return disputeService.getDisputeStatus(disputeId);
    }

    @GetMapping("/all")
    public List<Dispute> getAllDisputes() {
        return disputeService.getAllDisputes();
    }
}