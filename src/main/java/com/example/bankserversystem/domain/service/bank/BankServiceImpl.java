package com.example.bankserversystem.domain.service.bank;

import com.example.bankserversystem.domain.repository.bank.BankRepository;
import com.example.bankserversystem.dto.bank.BankResponse;
import com.example.bankserversystem.dto.bank.CreateBankRequest;
import com.example.bankserversystem.entity.bank.Bank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BankServiceImpl implements BankService{
    private final BankRepository bankRepository;
    public List<BankResponse> getNearBank(Double lat, Double lon) {
        return bankRepository.getNearBank(lat, lon)
                .stream().map(BankResponse::toResponse).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Boolean createBank(CreateBankRequest createBankRequest) {
        Bank bank = new Bank();
        bank.setBank(createBankRequest);
        bankRepository.save(bank);
        return true;
    }
}
