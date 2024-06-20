package com.example.bankserversystem.domain.controller;

import com.example.bankserversystem.domain.service.AccountService;
import com.example.bankserversystem.dto.ErrorResponse;
import com.example.bankserversystem.dto.Response;
import com.example.bankserversystem.dto.account.*;
import com.example.bankserversystem.enums.APIResponseCode;
import com.example.bankserversystem.exception.account.AccountException;
import com.example.bankserversystem.exception.user.UserInfoException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/account")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/search/myaccount/{userId}")
    public Response<List<AccountResponse>> getMyAccountList(@PathVariable Long userId) {
        return new Response<>(
                APIResponseCode.OK,
                APIResponseCode.OK_MESSAGE,
                accountService.getMyAccountList(userId)
        );
    }

    @PostMapping("/create/account")
    public Response<Void> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        accountService.createAccount(
                createAccountRequest.getDepositId(),
                createAccountRequest.getUserId());
        return new Response<>(
                APIResponseCode.OK,
                APIResponseCode.OK_MESSAGE
        );
    }

    @DeleteMapping("/delete/myaccount")
    public Response<Void> deleteAccount(@RequestBody DeleteAccountRequest deleteAccountRequest) {
        accountService.deleteAccount(deleteAccountRequest);
        return new Response<>(
                APIResponseCode.OK,
                APIResponseCode.OK_MESSAGE,
                null
        );
    }

    @PutMapping("/myaccount/deposit")
    public Response<AccountMoneyResponse> deposit(@RequestBody AccountMoneyRequest accountMoneyRequest) {
        return new Response<>(
                APIResponseCode.OK,
                APIResponseCode.OK_MESSAGE,
                accountService.deposit(accountMoneyRequest)
        );
    }

    @PutMapping("/myaccount/withdraw")
    public Response<AccountMoneyResponse> withdraw(@RequestBody AccountMoneyRequest accountMoneyRequest) {
        return new Response<>(
                APIResponseCode.OK,
                APIResponseCode.OK_MESSAGE,
                accountService.withdraw(accountMoneyRequest)
        );
    }

    @ExceptionHandler(AccountException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(AccountException e, HttpServletRequest request) {
        return new ErrorResponse(
                e.getAccountCode().getMessage(),
                e.getDetailMessage()
        );
    }
}
