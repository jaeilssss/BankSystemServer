package com.example.bankserversystem.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AccountCode {
    CRETE_ACCOUNT_ERROR("계좌 계설에 오류가 발생했습니다."),
    INVALID_REQUEST("유효하지 않은 요청입니다."),
    NOT_FOUND_ACCOUNT_NUMBER("올바르지 않은 계좌번호 입니다."),
    LACK_OF_DEPOSIT("출금이 불가능 합니다.(잔액 부족)");
    private final String message;

}
