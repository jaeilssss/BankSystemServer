package com.example.bankserversystem.domain.service;

import com.example.bankserversystem.domain.repository.AccountHistoryRepository;
import com.example.bankserversystem.entity.account.AccountHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountHistoryService {
    private final AccountHistoryRepository accountHistoryRepository;

/*    public List<AccountHistory> getMyAccountHistory(Long accountId) {

    }*/

}
