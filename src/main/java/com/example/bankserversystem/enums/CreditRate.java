package com.example.bankserversystem.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CreditRate {

    CREDIT_DELIQUENT("신용 불량자"),
    BASIC("일반"),
    GREAT("우수"),
    BEST("최우수");

    private final String creditRate;
}
