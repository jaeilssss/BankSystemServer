package com.example.bankserversystem.domain.controller;

import com.example.bankserversystem.domain.logic.JWTAndUserIdChecker;
import com.example.bankserversystem.domain.service.bank.BankServiceImpl;
import com.example.bankserversystem.dto.Response;
import com.example.bankserversystem.dto.bank.BankResponse;
import com.example.bankserversystem.globals.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/bank")
public class BankController extends BaseController {
    private final BankServiceImpl bankService;
    public BankController(JWTAndUserIdChecker jwtAndUserIdChecker, BankServiceImpl bankService) {
        super(jwtAndUserIdChecker);
        this.bankService = bankService;
    }

    @GetMapping
    public Response<List<BankResponse>> getNearBank(@RequestParam("lat") Double lat, @RequestParam("lon") Double lon) {
        return null;
    }
}
