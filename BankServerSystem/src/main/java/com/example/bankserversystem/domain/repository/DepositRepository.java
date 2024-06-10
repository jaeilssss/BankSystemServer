package com.example.bankserversystem.domain.repository;

import com.example.bankserversystem.entity.deposit.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
}
