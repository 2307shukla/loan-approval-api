package com.abhijeet.loan_approval_api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Loan Approval API",
        version = "1.0",
        description = "Loan Approval REST API by Abhijeet Shukla"
))
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class LoanApprovalApiApplication {

    private static final Logger logger =
        LoggerFactory.getLogger(LoanApprovalApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LoanApprovalApiApplication.class, args);
        logger.info("🚀 Loan Approval API Started Successfully!");
        logger.info("📖 Swagger UI: http://localhost:8080/swagger-ui.html");
        logger.info("🗄️ H2 Console: http://localhost:8080/h2-console");
    }
}