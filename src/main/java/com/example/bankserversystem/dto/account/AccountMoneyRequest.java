package com.example.bankserversystem.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class AccountMoneyRequest {
    private Long userId;
    private String accountNumber;
    private int depositMoney;


}
