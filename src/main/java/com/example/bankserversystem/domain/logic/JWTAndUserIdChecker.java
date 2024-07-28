package com.example.bankserversystem.domain.logic;

import com.example.bankserversystem.entity.user.UserInfo;
import com.example.bankserversystem.enums.UserInfoCode;
import com.example.bankserversystem.exception.user.UserInfoException;
import com.example.bankserversystem.globals.enums.BaseErrorCode;
import com.example.bankserversystem.globals.exception.MyException;
import com.example.bankserversystem.jwt.JwtProviders;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class JWTAndUserIdChecker {

    private final JwtProviders jwtProviders;
    public void check(Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long savedUserId = (Long) authentication.getPrincipal();

        if(savedUserId == null) throw new MyException(
                BaseErrorCode.BAD_REQUEST.getCode(), BaseErrorCode.BAD_REQUEST.getMessage());

        if(!savedUserId.equals(userId)) throw new MyException(
                BaseErrorCode.BAD_REQUEST.getCode(), BaseErrorCode.BAD_REQUEST.getMessage());

    }
}
