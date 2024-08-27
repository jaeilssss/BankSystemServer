package com.example.bankserversystem.domain.service.bank;

import com.example.bankserversystem.dto.bank.BankResponse;
import com.example.bankserversystem.dto.bank.CreateBankRequest;

import java.util.List;

public interface BankService {
    public List<BankResponse> getNearBank(Double lat, Double lon);
    public Boolean createBank(CreateBankRequest createBankRequest);
}
