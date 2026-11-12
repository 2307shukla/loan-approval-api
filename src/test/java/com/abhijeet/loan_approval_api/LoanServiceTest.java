package com.abhijeet.loan_approval_api;

import com.abhijeet.loan_approval_api.entity.LoanApplication;
import com.abhijeet.loan_approval_api.repository.LoanRepository;
import com.abhijeet.loan_approval_api.service.LoanService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanService loanService;

    @Test
    void testApplyLoan_Approved() {
        LoanApplication mockApp = new LoanApplication();
        mockApp.setCustomerId("A001");
        mockApp.setLoanAmount(500000.0);
        mockApp.setPurpose("Home Loan");
        mockApp.setDecision("APPROVED");

        when(loanRepository.save(any())).thenReturn(mockApp);

        LoanApplication result = loanService.applyLoan(
            "A001", 500000.0, "Home Loan");

        assertEquals("APPROVED", result.getDecision());
    }

    @Test
    void testApplyLoan_Rejected() {
        LoanApplication mockApp = new LoanApplication();
        mockApp.setCustomerId("A001");
        mockApp.setLoanAmount(600000.0);
        mockApp.setPurpose("Home Loan");
        mockApp.setDecision("REJECTED");

        when(loanRepository.save(any())).thenReturn(mockApp);

        LoanApplication result = loanService.applyLoan(
            "A001", 600000.0, "Home Loan");

        assertEquals("REJECTED", result.getDecision());
    }

    @Test
    void testApplyLoan_EmptyCustomerId() {
        assertThrows(IllegalArgumentException.class, () -> {
            loanService.applyLoan("", 500000.0, "Home Loan");
        });
    }

    @Test
    void testApplyLoan_NegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> {
            loanService.applyLoan("A001", -1000.0, "Home Loan");
        });
    }
}