package com.example.bankserversystem.domain.repository;

import com.example.bankserversystem.entity.account.AccountHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountHistoryRepository extends JpaRepository<AccountHistory, Long> {

    Optional<List<AccountHistory>> findByAccountNumber(String accountNumber);

}
