package com.abhijeet.loan_approval_api.security;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    private Bucket createNewBucket() {
        Bandwidth limit = Bandwidth.classic(10, 
            Refill.greedy(10, Duration.ofMinutes(1)));
        return Bucket.builder().addLimit(limit).build();
    }

    // Upper bound on tracked client IPs so the map cannot grow without limit.
    private static final int MAX_TRACKED_CLIENTS = 10_000;

    private Bucket getBucket(String ip) {
        if (buckets.size() >= MAX_TRACKED_CLIENTS) {
            buckets.clear();
        }
        return buckets.computeIfAbsent(ip, k -> createNewBucket());
    }

    // Platform health checks poll /actuator/* often; they must never be throttled,
    // or the platform would mark a healthy instance as down.
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getRequestURI().startsWith("/actuator/");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String ip = request.getRemoteAddr();
        Bucket bucket = getBucket(ip);

        if (bucket.tryConsume(1)) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.setContentType("application/json");
            response.getWriter().write(
                "{\"status\":429,\"error\":\"Too Many Requests\"," +
                "\"message\":\"Rate limit exceeded. Max 10 requests per minute.\"}");
        }
    }
}
