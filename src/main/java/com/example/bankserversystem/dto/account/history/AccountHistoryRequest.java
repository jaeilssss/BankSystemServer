package com.example.bankserversystem.dto.account.history;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountHistoryRequest {
    private Long userId;
    private Long accountId;
    private String accountNumber;
}
