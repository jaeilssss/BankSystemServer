package com.example.bankserversystem.exception.account;

import com.example.bankserversystem.enums.AccountCode;
import org.springframework.security.core.parameters.P;

public class AccountException extends RuntimeException {
    private AccountCode accountCode;
    private String detailMessage;

    public AccountException(AccountCode accountCode, String detailMessage) {
        this.accountCode = accountCode;
        this.detailMessage = detailMessage;
    }

    public AccountException(AccountCode accountCode) {
        this.accountCode = accountCode;
        this.detailMessage = accountCode.getMessage();
    }
}
