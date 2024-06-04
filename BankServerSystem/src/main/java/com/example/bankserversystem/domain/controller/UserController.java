package com.example.bankserversystem.domain.controller;

import com.example.bankserversystem.domain.service.UserInfoService;
import com.example.bankserversystem.dto.ErrorResponse;
import com.example.bankserversystem.dto.Response;
import com.example.bankserversystem.dto.user.LoginRequest;
import com.example.bankserversystem.dto.user.UserInfoRequest;
import com.example.bankserversystem.dto.user.UserInfoResponse;
import com.example.bankserversystem.enums.APIResponseCode;
import com.example.bankserversystem.exception.user.UserInfoException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {

    private final UserInfoService userInfoService;


    @PostMapping("/signup")
    public Response<UserInfoResponse> signUp(@RequestBody UserInfoRequest userInfoRequest) {
        return new Response<UserInfoResponse>(
                APIResponseCode.OK,
                APIResponseCode.OK_MESSAGE,
                userInfoService.signUp(userInfoRequest)
        );
    }

    @PostMapping("/login")
    public Response<String> login(@RequestBody LoginRequest loginRequest) {
        return new Response<>(
                APIResponseCode.OK,
                APIResponseCode.OK_MESSAGE,
                userInfoService.login(loginRequest.getEmail(), loginRequest.getPassword())
        );
    }

    @GetMapping("/{userId}")
    public Response<UserInfoResponse> search(@PathVariable Long userId) {
        return new Response<UserInfoResponse>(
                APIResponseCode.OK,
                APIResponseCode.OK_MESSAGE,
                userInfoService.getUserInfoDetail(userId)
        );
    }


    @PutMapping("/{userId}")
    public Response<UserInfoResponse> modifyUserInfo(@PathVariable Long userId, @RequestBody UserInfoRequest userInfoRequest) {
        return new Response<>(
                APIResponseCode.OK,
                APIResponseCode.OK_MESSAGE,
                userInfoService.modifyUserInfo(userId, userInfoRequest)
        );
    }


    @ExceptionHandler(UserInfoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(UserInfoException e, HttpServletRequest request) {
        log.error("errorCode : {} , url : {} , message {}",e.getUserInfoCode() , request.getRequestURI() , e.getDetailMessage());
        return new ErrorResponse(
                e.getUserInfoCode().getCode(),
                e.getUserInfoCode().getMessage()
        );
    }
}
