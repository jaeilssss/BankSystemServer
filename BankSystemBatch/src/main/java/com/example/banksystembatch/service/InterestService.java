package com.example.banksystembatch.service;

import com.example.banksystembatch.entity.Account;
import com.example.banksystembatch.entity.AccountHistory;
import com.example.banksystembatch.repository.AccountHistoryRepository;
import com.example.banksystembatch.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterestService {
    private final AccountHistoryRepository accountHistoryRepository;

    public void saveAccountHistory(AccountHistory accountHistory) {
        accountHistoryRepository.save(accountHistory);
    }

}
