package com.example.legaldocassistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.legaldocassistant")
@EntityScan(basePackages = "com.example.legaldocassistant.model")
@EnableJpaRepositories(basePackages = "com.example.legaldocassistant.repository")
public class LegalDocAssistantApplication {

    public static void main(String[] args) {
        SpringApplication.run(LegalDocAssistantApplication.class, args);
    }
}
