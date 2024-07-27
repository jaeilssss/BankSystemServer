package com.example.bankserversystem.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DepositCode {

    NO_DEPOSIT_DATA("No_deposit_data");

    private final String message;
}
