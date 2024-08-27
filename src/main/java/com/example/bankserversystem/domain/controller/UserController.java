package com.example.bankserversystem.domain.controller;

import com.example.bankserversystem.domain.logic.JWTAndUserIdChecker;
import com.example.bankserversystem.domain.service.userinfo.UserInfoService;
import com.example.bankserversystem.domain.service.userinfo.UserInfoServiceImpl;
import com.example.bankserversystem.dto.Response;
import com.example.bankserversystem.dto.user.LoginRequest;
import com.example.bankserversystem.dto.user.UserInfoRequest;
import com.example.bankserversystem.dto.user.UserInfoResponse;
import com.example.bankserversystem.enums.APIResponseCode;
import com.example.bankserversystem.globals.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/user")
public class UserController extends BaseController {

    private final UserInfoService userInfoService;

    public UserController(
            JWTAndUserIdChecker jwtAndUserIdChecker,
            UserInfoService userInfoService) {
        super(jwtAndUserIdChecker);
        this.userInfoService = userInfoService;
    }

    @PostMapping("/signup")
    public Response<UserInfoResponse> signUp(@RequestBody UserInfoRequest userInfoRequest) {
        System.out.println("signUP!!!");
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
        jwtAndUserIdCheck(userId);
        return new Response<UserInfoResponse>(
                APIResponseCode.OK,
                APIResponseCode.OK_MESSAGE,
                userInfoService.getUserInfoDetail(userId)
        );
    }


    @PutMapping("/{userId}")
    public Response<UserInfoResponse> modifyUserInfo(@PathVariable Long userId, @RequestBody UserInfoRequest userInfoRequest) {
        jwtAndUserIdCheck(userId);
        return new Response<>(
                APIResponseCode.OK,
                APIResponseCode.OK_MESSAGE,
                userInfoService.modifyUserInfo(userId, userInfoRequest)
        );
    }

}
