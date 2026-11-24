package com.abhijeet.loan_approval_api;

import com.abhijeet.loan_approval_api.security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private static final String GOOD_SECRET = "test-only-secret-not-used-anywhere-else-0123456789";

    private JwtUtil util(String secret, long expirationMs) {
        JwtUtil util = new JwtUtil();
        ReflectionTestUtils.setField(util, "secret", secret);
        ReflectionTestUtils.setField(util, "expiration", expirationMs);
        return util;
    }

    @Test
    void tokenRoundTrips() {
        JwtUtil util = util(GOOD_SECRET, 60_000);
        util.validateSecret();
        String token = util.generateToken("alice");
        assertTrue(util.validateToken(token));
        assertEquals("alice", util.extractUsername(token));
    }

    @Test
    void shortSecretIsRejectedAtStartup() {
        JwtUtil util = util("too-short", 60_000);
        assertThrows(IllegalStateException.class, util::validateSecret);
    }

    @Test
    void missingSecretIsRejectedAtStartup() {
        JwtUtil util = util(null, 60_000);
        assertThrows(IllegalStateException.class, util::validateSecret);
    }

    @Test
    void tokenSignedWithAnotherKeyIsRejected() {
        JwtUtil signer = util("another-secret-another-secret-another-secret-1234", 60_000);
        JwtUtil verifier = util(GOOD_SECRET, 60_000);
        assertFalse(verifier.validateToken(signer.generateToken("mallory")));
    }

    @Test
    void expiredTokenIsRejected() {
        JwtUtil util = util(GOOD_SECRET, -1_000);
        assertFalse(util.validateToken(util.generateToken("alice")));
    }
}
