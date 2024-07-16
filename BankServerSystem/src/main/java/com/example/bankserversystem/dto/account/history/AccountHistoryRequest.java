package com.example.bankserversystem.dto.account.history;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class AccountHistoryRequest {
    private Long userId;
    private Long accountId;
    private String accountNumber;
}
