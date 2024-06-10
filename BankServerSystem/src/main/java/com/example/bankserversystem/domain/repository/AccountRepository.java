package com.example.bankserversystem.domain.repository;


import com.example.bankserversystem.entity.deposit.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
