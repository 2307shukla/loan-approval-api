package com.abhijeet.loan_approval_api;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dispute")
public class DisputeController {

    @PostMapping("/raise")
    public Map<String, Object> raiseDispute(
            @RequestParam String customerId,
            @RequestParam String applicationId,
            @RequestParam String reason,
            @RequestParam String description) {

        Map<String, Object> response = new HashMap<>();

        // Generate dispute ID
        String disputeId = "DISP-" + System.currentTimeMillis();

        // Route based on reason
        String assignedTo;
        String priority;

        switch (reason.toUpperCase()) {
            case "WRONGREJECTION":
                assignedTo = "Senior Credit Manager";
                priority = "HIGH";
                break;
            case "INTERESTRATE":
                assignedTo = "Loan Products Team";
                priority = "MEDIUM";
                break;
            case "DOCUMENTISSUE":
                assignedTo = "Documentation Team";
                priority = "LOW";
                break;
            default:
                assignedTo = "General Resolution Team";
                priority = "MEDIUM";
        }

        response.put("disputeId", disputeId);
        response.put("customerId", customerId);
        response.put("applicationId", applicationId);
        response.put("reason", reason);
        response.put("description", description);
        response.put("status", "OPEN");
        response.put("assignedTo", assignedTo);
        response.put("priority", priority);
        response.put("message", 
            "Dispute raised successfully. You will hear back within 48 hours.");

        return response;
    }

    @GetMapping("/status/{disputeId}")
    public Map<String, Object> getDisputeStatus(
            @PathVariable String disputeId) {

        Map<String, Object> response = new HashMap<>();
        response.put("disputeId", disputeId);
        response.put("status", "UNDER_REVIEW");
        response.put("assignedTo", "Senior Credit Manager");
        response.put("expectedResolution", "Within 48 hours");
        response.put("message", 
            "Your dispute is being reviewed by our team");
        return response;
    }

    @PutMapping("/resolve/{disputeId}")
    public Map<String, Object> resolveDispute(
            @PathVariable String disputeId,
            @RequestParam String resolution,
            @RequestParam String resolvedBy) {

        Map<String, Object> response = new HashMap<>();
        response.put("disputeId", disputeId);
        response.put("status", "RESOLVED");
        response.put("resolution", resolution);
        response.put("resolvedBy", resolvedBy);
        response.put("message", 
            "Dispute has been resolved successfully");
        return response;
    }
}