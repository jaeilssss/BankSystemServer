package com.example.bankserversystem.domain.service.deposit;

import com.example.bankserversystem.dto.deposit.CreateDepositRequest;
import com.example.bankserversystem.dto.deposit.DepositResponse;

import java.util.List;

public interface DepositService {
    public List<DepositResponse> getAllDepositData();
    public DepositResponse createDeposit(CreateDepositRequest createDeposit);

}

