package com.example.bankserversystem.domain.service.account;

import com.example.bankserversystem.domain.repository.account.AccountHistoryRepository;
import com.example.bankserversystem.dto.account.history.AccountHistoryRequest;
import com.example.bankserversystem.dto.account.history.AccountHistoryResponse;
import com.example.bankserversystem.enums.AccountCode;
import com.example.bankserversystem.globals.exception.MyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AccountHistoryServiceImpl implements AccountHistoryService{
    private final AccountHistoryRepository accountHistoryRepository;

    public List<AccountHistoryResponse> getMyAccountHistory(AccountHistoryRequest request) {
        return accountHistoryRepository.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> new MyException(
                        AccountCode.NOT_FOUND_ACCOUNT_NUMBER.getCode(),
                        AccountCode.NOT_FOUND_ACCOUNT_NUMBER.getMessage()))
                .stream().map(AccountHistoryResponse::makeAccountHistoryResponseFromAccountHistory)
                .collect(Collectors.toList());
    }

}
