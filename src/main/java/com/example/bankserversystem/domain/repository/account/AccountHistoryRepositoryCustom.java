package com.example.bankserversystem.domain.repository.account;

import com.example.bankserversystem.entity.account.AccountHistory;

import java.util.List;
import java.util.Optional;

public interface AccountHistoryRepositoryCustom {
    Optional<List<AccountHistory>> findByAccountNumber(String accountNumber);
}
