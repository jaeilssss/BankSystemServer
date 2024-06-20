package com.example.bankserversystem.domain.controller;

import com.example.bankserversystem.domain.service.DepositService;
import com.example.bankserversystem.dto.ErrorResponse;
import com.example.bankserversystem.dto.Response;
import com.example.bankserversystem.dto.TransferResponse;
import com.example.bankserversystem.dto.deposit.CreateDeposit;
import com.example.bankserversystem.dto.deposit.DepositResponse;
import com.example.bankserversystem.dto.deposit.SignUpDepositRequest;
import com.example.bankserversystem.entity.deposit.Deposit;
import com.example.bankserversystem.enums.APIResponseCode;
import com.example.bankserversystem.exception.account.AccountException;
import com.example.bankserversystem.exception.deposit.DepositException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/deposit")
public class DepositController {

    private final DepositService depositService;

    @GetMapping
    public Response<List<DepositResponse>> getAllDepositData() {
        return new Response<List<DepositResponse>>(
                APIResponseCode.OK,
                APIResponseCode.OK_MESSAGE,
                depositService.getAllDepositData()
                );
    }

    @PostMapping("/createDeposit")
    public Response<DepositResponse> createDeposit(@RequestBody CreateDeposit createDeposit) {
        return new Response<DepositResponse>(
                APIResponseCode.OK,
                APIResponseCode.OK_MESSAGE,
                depositService.createDeposit(createDeposit)
        );
    }

    @ExceptionHandler(DepositException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(DepositException e, HttpServletRequest request) {
        return new ErrorResponse(
                e.getDepositCode().getMessage(),
                e.getDetailMessage()
        );
    }

}
