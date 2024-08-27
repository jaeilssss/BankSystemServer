package com.example.bankserversystem.domain.repository.bank;

import com.example.bankserversystem.dto.bank.CreateBankRequest;
import com.example.bankserversystem.entity.bank.Bank;

import java.util.List;

public interface BankRepositoryCustom {
    public List<Bank> getNearBank(Double latitude, Double longitude);
}
