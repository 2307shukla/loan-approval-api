package com.abhijeet.loan_approval_api.controller;

import com.abhijeet.loan_approval_api.entity.Dispute;
import com.abhijeet.loan_approval_api.service.DisputeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dispute")
@Tag(name = "Dispute Controller", description = "APIs for Dispute Management")
public class DisputeController {

    @Autowired
    private DisputeService disputeService;

    @Operation(summary = "Raise a dispute",
               description = "Raise a new dispute for a customer")
    @PostMapping("/raise")
    public Dispute raiseDispute(
            @RequestParam String customerId,
            @RequestParam String reason) {
        return disputeService.raiseDispute(customerId, reason);
    }

    @Operation(summary = "Resolve a dispute",
               description = "Resolve an existing dispute by ID")
    @PutMapping("/resolve/{disputeId}")
    public Dispute resolveDispute(@PathVariable Long disputeId) {
        return disputeService.resolveDispute(disputeId);
    }

    @Operation(summary = "Get dispute status",
               description = "Get current status of a dispute by ID")
    @GetMapping("/status/{disputeId}")
    public Dispute getDisputeStatus(@PathVariable Long disputeId) {
        return disputeService.getDisputeStatus(disputeId);
    }

    @Operation(summary = "Get all disputes",
               description = "Returns list of all disputes")
    @GetMapping("/all")
    public List<Dispute> getAllDisputes() {
        return disputeService.getAllDisputes();
    }
}