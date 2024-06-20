package com.example.bankserversystem.dto.account.history;

import com.example.bankserversystem.entity.account.AccountHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class AccountHistoryResponse {
    private String accountNumber;
    private String historyMessage;
    private String type;
    private LocalDateTime createdAt;


    public static AccountHistoryResponse makeAccountHistoryResponseFromAccountHistory(AccountHistory accountHistory) {
        return AccountHistoryResponse.builder()
                .accountNumber(accountHistory.getAccountNumber())
                .historyMessage(accountHistory.getHistoryMessage())
                .type(accountHistory.getType())
                .createdAt(accountHistory.getCreatedAt())
                .build();
    }
}
