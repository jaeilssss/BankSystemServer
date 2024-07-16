package com.example.bankserversystem.dto.account;

import com.example.bankserversystem.entity.account.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class AccountResponse {
    private String depositName;
    private String depositTag;
    private Double interestRate;
    private String accountNumber;
    private String accountType;
    private int totalDeposit;

    public static AccountResponse makeAccountResponseFromAccount(Account account) {
        return AccountResponse.builder()
                .accountNumber(account.getAccountNumber())
                .depositName(account.getDeposit().getDepositName())
                .depositTag(account.getDeposit().getTag())
                .interestRate(account.getDeposit().getInterestRate())
                .accountType(account.getAccountType())
                .totalDeposit(account.getTotalDeposit())
                .build();
    }
}
