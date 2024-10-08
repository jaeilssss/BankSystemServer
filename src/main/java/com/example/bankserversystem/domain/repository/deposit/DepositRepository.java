package com.example.bankserversystem.domain.repository.deposit;

import com.example.bankserversystem.entity.deposit.Deposit;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
}
