package com.example.bankserversystem.domain.controller;

import com.example.bankserversystem.domain.logic.JWTAndUserIdChecker;
import com.example.bankserversystem.domain.service.DepositService;
import com.example.bankserversystem.dto.ErrorResponse;
import com.example.bankserversystem.dto.Response;
import com.example.bankserversystem.dto.deposit.CreateDepositRequest;
import com.example.bankserversystem.dto.deposit.DepositResponse;
import com.example.bankserversystem.enums.APIResponseCode;
import com.example.bankserversystem.exception.deposit.DepositException;
import com.example.bankserversystem.globals.controller.BaseController;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/deposit")
public class DepositController extends BaseController {

    private final DepositService depositService;

    public DepositController(
            JWTAndUserIdChecker jwtAndUserIdChecker,
            DepositService depositService) {
        super(jwtAndUserIdChecker);
        this.depositService = depositService;
    }

    @GetMapping
    public Response<List<DepositResponse>> getAllDepositData() {
        return new Response<List<DepositResponse>>(
                APIResponseCode.OK,
                APIResponseCode.OK_MESSAGE,
                depositService.getAllDepositData()
                );
    }

    @PostMapping("/createDeposit")
    public Response<DepositResponse> createDeposit(@RequestBody CreateDepositRequest createDeposit) {
        return new Response<DepositResponse>(
                APIResponseCode.OK,
                APIResponseCode.OK_MESSAGE,
                depositService.createDeposit(createDeposit)
        );
    }

}
