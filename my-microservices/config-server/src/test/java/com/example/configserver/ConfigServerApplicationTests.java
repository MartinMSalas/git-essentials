package com.example.configserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("native") // Activate the 'native' profile for tests
class ConfigServerApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
        // This test will pass if the application context loads successfully
    }

    @Test
    void testServiceConfigServed() {
        // Request the configuration for 'test-service' and default profile
        ResponseEntity<String> response = restTemplate.getForEntity("/test-service/default", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("sample.property");
        assertThat(response.getBody()).contains("Hello from test config!");
        assertThat(response.getBody()).contains("server.port: 8899"); // Check if YAML to Properties conversion works
    }
}
