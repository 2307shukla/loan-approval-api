package com.abhijeet.loan_approval_api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "jwt.secret=test-only-secret-not-used-anywhere-else-0123456789")
class LoanApprovalApiApplicationTests {

	@Test
	void contextLoads() {
	}

}
