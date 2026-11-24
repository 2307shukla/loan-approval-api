package com.abhijeet.loan_approval_api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = "jwt.secret=test-only-secret-not-used-anywhere-else-0123456789")
@AutoConfigureMockMvc
class ProductionSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void healthEndpointIsPublic() throws Exception {
        mockMvc.perform(get("/actuator/health")).andExpect(status().isOk());
    }

    @Test
    void businessEndpointsRequireAuthentication() throws Exception {
        mockMvc.perform(get("/api/credit/score").param("customerId", "A001"))
            .andExpect(status().isForbidden());
    }

    @Test
    void h2ConsoleIsNotExposedByDefault() throws Exception {
        // Not permitted by the security config, so an anonymous request is refused.
        mockMvc.perform(get("/h2-console/")).andExpect(status().isForbidden());
    }

    @Test
    void framingIsDenied() throws Exception {
        mockMvc.perform(get("/actuator/health")).andExpect(header().string("X-Frame-Options", "DENY"));
    }
}
