package com.example.bankserversystem.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserInfoCode {

    NO_USER_INFO("해당되는 유저가 없습니다","USER-001"),
    DUPLICATED_EMAIL("중복되는 유저 이메일이 있습니다","USER-002"),
    INTERNAL_SERVER_ERROR("서버 오류가 발생했습니다.","USER-003"),
    INVALID_REQUEST("잘못된 요청입니다.","USER-004"),
    ACCESS_TOKEN_EXPIRED("Access Token이 만료 되었습니다", "USER_005");

    private final String message;
    private final String code;
}
