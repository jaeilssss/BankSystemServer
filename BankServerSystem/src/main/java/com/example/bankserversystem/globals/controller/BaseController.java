package com.example.bankserversystem.globals.controller;

import com.example.bankserversystem.domain.logic.JWTAndUserIdChecker;
import com.example.bankserversystem.dto.Response;
import com.example.bankserversystem.globals.exception.MyException;
import com.example.bankserversystem.jwt.JwtProviders;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BaseController {
    private final JWTAndUserIdChecker jwtAndUserIdChecker;

    public void jwtAndUserIdCheck(Long userId) {
        jwtAndUserIdChecker.check(userId);
    }
    @ExceptionHandler(MyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<Boolean> handleException(MyException e, HttpServletRequest request) {
        log.error("error code : " + e.getExceptionCode()+ "  error message : "+e.getExceptionMessage());
        return new Response<>(
                e.getExceptionCode(),
                e.getExceptionMessage(),
                false
        );
    }
}
