package com.example.bankserversystem.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class AccountMoneyResponse {
    private String accountNumber;
    private String historyMessage;
    private LocalDateTime createdAt;

    public static AccountMoneyResponse makeDepositAccountResponse(
            String accountNumber,
            String historyMessage,
            LocalDateTime createdAt
    ) {
        return AccountMoneyResponse.builder()
                .accountNumber(accountNumber)
                .historyMessage(historyMessage)
                .createdAt(createdAt)
                .build();
    }
}
