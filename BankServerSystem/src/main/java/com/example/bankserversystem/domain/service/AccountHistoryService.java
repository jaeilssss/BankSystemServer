package com.example.bankserversystem.domain.service;

import com.example.bankserversystem.domain.repository.AccountHistoryRepository;
import com.example.bankserversystem.dto.account.AccountResponse;
import com.example.bankserversystem.dto.account.history.AccountHistoryRequest;
import com.example.bankserversystem.dto.account.history.AccountHistoryResponse;
import com.example.bankserversystem.entity.account.AccountHistory;
import com.example.bankserversystem.enums.AccountCode;
import com.example.bankserversystem.exception.account.AccountException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AccountHistoryService {
    private final AccountHistoryRepository accountHistoryRepository;

    public List<AccountHistoryResponse> getMyAccountHistory(AccountHistoryRequest request) {
        return accountHistoryRepository.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> new AccountException(AccountCode.NOT_FOUND_ACCOUNT_NUMBER))
                .stream().map(AccountHistoryResponse::makeAccountHistoryResponseFromAccountHistory)
                .collect(Collectors.toList());
    }


}
