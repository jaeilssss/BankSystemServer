package com.example.bankserversystem.exception.deposit;

import com.example.bankserversystem.enums.DepositCode;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DepositException extends RuntimeException{

    private DepositCode depositCode;
    private String detailMessage;

    public DepositException(DepositCode depositCode, String detailMessage) {
        this.depositCode = depositCode;
        this.detailMessage = detailMessage;
    }

    public DepositException(DepositCode depositCode) {
        this.depositCode = depositCode;
        this.detailMessage = depositCode.getMessage();
    }
}
