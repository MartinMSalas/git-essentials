package com.example.personaladminservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // To register with Eureka
public class PersonalAdminServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalAdminServiceApplication.class, args);
    }

}
