package com.example.bankserversystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableJpaRepositories(basePackages = {"com.example.bankserversystem.domain.repository"})
//@EntityScan(basePackages = {"com.example.bankserversystem.entity"})
@SpringBootApplication
public class BankServerSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankServerSystemApplication.class, args);
    }

}
