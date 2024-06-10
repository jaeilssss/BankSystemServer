package com.example.bankserversystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BankServerSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankServerSystemApplication.class, args);
    }

}
