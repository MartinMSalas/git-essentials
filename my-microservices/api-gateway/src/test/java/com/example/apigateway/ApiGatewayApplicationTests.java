package com.example.apigateway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") // Activate the 'test' profile to disable Eureka
class ApiGatewayApplicationTests {

    // If you had WebTestClient, you could autowire it here:
    // @Autowired
    // private WebTestClient webTestClient;

    @Test
    void contextLoads() {
        // This test will pass if the application context loads successfully
        // Given eureka.client.enabled=false, it won't try to connect to Eureka
    }

    /*
    // Example of a simple route test (would require a bit more setup or a running downstream service)
    @Test
    void healthEndpointReturnsOk() {
        // Spring Boot Actuator's health endpoint is often at /actuator/health
        // This assumes a route exists or discovery locator can find a service named 'ACTUATOR-SERVICE'
        // or you have a specific route to it.
        // For a simple context load, this level of testing is not strictly needed for this subtask.

        // webTestClient.get().uri("/actuator/health") // Or a path to a known service through the gateway
        //     .exchange()
        //     .expectStatus().isOk();
    }
    */
}
