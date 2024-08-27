package com.example.bankserversystem.domain.controller;

import com.example.bankserversystem.domain.logic.JWTAndUserIdChecker;
import com.example.bankserversystem.domain.service.bank.BankService;
import com.example.bankserversystem.domain.service.bank.BankServiceImpl;
import com.example.bankserversystem.dto.Response;
import com.example.bankserversystem.dto.bank.BankResponse;
import com.example.bankserversystem.dto.bank.CreateBankRequest;
import com.example.bankserversystem.enums.APIResponseCode;
import com.example.bankserversystem.globals.controller.BaseController;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/bank")
public class BankController extends BaseController {
    private final BankService bankService;
    public BankController(JWTAndUserIdChecker jwtAndUserIdChecker, BankServiceImpl bankService) {
        super(jwtAndUserIdChecker);
        this.bankService = bankService;
    }

    @PostMapping("/create")
    public Response<Boolean> createBank(@RequestBody CreateBankRequest createBankRequest) {
        bankService.createBank(createBankRequest);
        return new Response<>(
                APIResponseCode.OK,
                APIResponseCode.OK_MESSAGE,
                true
        );
    }

    @GetMapping
    public Response<List<BankResponse>> getNearBank(@RequestParam("lat") Double lat, @RequestParam("lon") Double lon) {
        return new Response<>(
                APIResponseCode.OK,
                APIResponseCode.OK_MESSAGE,
                bankService.getNearBank(lat, lon)
        );
    }
}
