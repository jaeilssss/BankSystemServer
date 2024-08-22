package com.example.bankserversystem.domain.repository.bank;

import com.example.bankserversystem.entity.bank.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {
}
