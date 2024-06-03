package com.example.bankserversystem.exception.user;

import com.example.bankserversystem.enums.UserInfoCode;
import lombok.Getter;

@Getter
public class UserInfoException extends RuntimeException{

    private UserInfoCode userInfoCode;
    private String detailMessage;

    public UserInfoException(UserInfoCode errorCode) {
        // 이걸 꼭 넣어줘야하나?
        super(errorCode.getMessage());
        this.userInfoCode = errorCode;
        this.detailMessage = errorCode.getMessage();
    }

    public UserInfoException(UserInfoCode errorCode, String detailMessage) {
        super(errorCode.getMessage());
        this.userInfoCode = errorCode;
        this.detailMessage = detailMessage;
    }
}
