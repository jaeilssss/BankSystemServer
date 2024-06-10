package com.example.bankserversystem.domain.controller;

import com.example.bankserversystem.domain.service.AccountService;
import com.example.bankserversystem.dto.Response;
import com.example.bankserversystem.dto.account.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/search/myaccount/{userId}")
    public Response<List<AccountResponse>> getMyAccountList(@PathVariable Long userId) {

    }
}
