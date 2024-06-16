package com.example.bankserversystem.domain.repository;

import com.example.bankserversystem.entity.account.AccountHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHistoryRepository extends JpaRepository<AccountHistory, Long> {
}
