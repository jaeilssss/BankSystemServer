package com.example.bankserversystem.domain.controller;

import com.example.bankserversystem.domain.logic.JWTAndUserIdChecker;
import com.example.bankserversystem.domain.service.AccountHistoryService;
import com.example.bankserversystem.dto.Response;
import com.example.bankserversystem.dto.account.history.AccountHistoryRequest;
import com.example.bankserversystem.dto.account.history.AccountHistoryResponse;
import com.example.bankserversystem.entity.account.AccountHistory;
import com.example.bankserversystem.enums.APIResponseCode;
import com.example.bankserversystem.globals.controller.BaseController;
import com.example.bankserversystem.jwt.JwtAuthenticationFilter;
import com.example.bankserversystem.jwt.JwtProviders;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/account/history")
public class AccountHistoryController extends BaseController {

    private final AccountHistoryService accountHistoryService;

    @Autowired
    public AccountHistoryController(
            AccountHistoryService accountHistoryService,
            JWTAndUserIdChecker jwtAndUserIdChecker) {
        super(jwtAndUserIdChecker);
        this.accountHistoryService = accountHistoryService;
    }

    @GetMapping("/test")
    public Response<List<AccountHistoryResponse>> getMyAccountHistory(@RequestBody AccountHistoryRequest request) {
        jwtAndUserIdCheck(request.getUserId());
        return new Response<>(
                APIResponseCode.OK,
                APIResponseCode.OK_MESSAGE,
              accountHistoryService.getMyAccountHistory(request)
        );
    }
}
