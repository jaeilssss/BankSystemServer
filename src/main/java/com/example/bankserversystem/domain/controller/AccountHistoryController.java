package com.example.bankserversystem.domain.controller;

import com.example.bankserversystem.domain.logic.JWTAndUserIdChecker;
import com.example.bankserversystem.domain.service.account.AccountHistoryService;
import com.example.bankserversystem.domain.service.account.AccountHistoryServiceImpl;
import com.example.bankserversystem.dto.Response;
import com.example.bankserversystem.dto.account.history.AccountHistoryRequest;
import com.example.bankserversystem.dto.account.history.AccountHistoryResponse;
import com.example.bankserversystem.enums.APIResponseCode;
import com.example.bankserversystem.globals.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/get/histogry")
    public Response<List<AccountHistoryResponse>> getMyAccountHistory(@RequestBody AccountHistoryRequest request) {
        jwtAndUserIdCheck(request.getUserId());
        return new Response<>(
                APIResponseCode.OK,
                APIResponseCode.OK_MESSAGE,
              accountHistoryService.getMyAccountHistory(request)
        );
    }
}
