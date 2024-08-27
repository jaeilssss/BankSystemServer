package com.example.bankserversystem.domain.controller;

import com.example.bankserversystem.domain.logic.JWTAndUserIdChecker;
import com.example.bankserversystem.domain.service.account.AccountService;
import com.example.bankserversystem.domain.service.account.AccountServiceImpl;
import com.example.bankserversystem.dto.Response;
import com.example.bankserversystem.dto.account.*;
import com.example.bankserversystem.enums.APIResponseCode;
import com.example.bankserversystem.globals.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("api/account")
public class AccountController extends BaseController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService, JWTAndUserIdChecker jwtAndUserIdChecker) {
        super(jwtAndUserIdChecker);
        this.accountService = accountService;
    }


    @GetMapping("/search/myaccount/{userId}")
    public Response<List<AccountResponse>> getMyAccountList(@PathVariable("userId") Long userId) {
        //userId와 토큰의 Id값을 비교
        jwtAndUserIdCheck(userId);
        return new Response<>(
                APIResponseCode.OK,
                APIResponseCode.OK_MESSAGE,
                accountService.getMyAccountList(userId)
        );
    }



    @PostMapping("/create/account")
    public Response<Void> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        // request에 들어있는 UserId가 JWT에 있는 UserId와 일치한지 체크
        jwtAndUserIdCheck(createAccountRequest.getUserId());
        accountService.createAccount(createAccountRequest);
        return new Response<>(
                APIResponseCode.OK,
                APIResponseCode.OK_MESSAGE
        );
    }

    @DeleteMapping("/delete/myaccount")
    public Response<Void> deleteAccount(@RequestBody DeleteAccountRequest deleteAccountRequest) {
        jwtAndUserIdCheck(deleteAccountRequest.getUserId());
        accountService.deleteAccount(deleteAccountRequest);
        return new Response<>(
                APIResponseCode.OK,
                APIResponseCode.OK_MESSAGE,
                null
        );
    }

    @PutMapping("/myaccount/deposit")
    public Response<AccountMoneyResponse> deposit(@RequestBody AccountMoneyRequest accountMoneyRequest) {
        jwtAndUserIdCheck(accountMoneyRequest.getUserId());
        return new Response<>(
                APIResponseCode.OK,
                APIResponseCode.OK_MESSAGE,
                accountService.deposit(accountMoneyRequest)
        );
    }

    @PutMapping("/myaccount/withdraw")
    public Response<AccountMoneyResponse> withdraw(@RequestBody AccountMoneyRequest accountMoneyRequest) {
        jwtAndUserIdCheck(accountMoneyRequest.getUserId());
        return new Response<>(
                APIResponseCode.OK,
                APIResponseCode.OK_MESSAGE,
                accountService.withdraw(accountMoneyRequest)
        );
    }


}
