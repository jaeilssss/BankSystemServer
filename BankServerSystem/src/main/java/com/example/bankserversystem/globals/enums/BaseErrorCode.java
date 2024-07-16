package com.example.bankserversystem.globals.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseErrorCode {
    BAD_REQUEST("BASE_001", "잘못된 요청 입니다.");
    private final String code;
    private final String message;
}
