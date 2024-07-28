package com.example.bankserversystem.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum CardErrorCode {
    NO_PROVIDE_OTHER_COMPANY("Card_001", "다른 카드사 결제는 제공하지않습니다."),
    NOT_ENOUGH_MONEY("Card_002", "잔액이 부족합니다"),
    CREATE_CARD_ERROR("Card_003","카드 생성에 실패했습니다. 다시 시도해주세요"),
    ERROR("Card_000", "에러가 발생했습니다."),
    NOT_MATCH_PASSWORD("Card_004","비밀번호가 맞지 않습니다."),
    INVALIDATE_EXPIRATION("Card_005","카드 유효기간이 지났습니다");

    private final String code;
    private final String message;
}
