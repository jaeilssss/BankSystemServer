package com.example.bankserversystem.domain.service.bank;

import com.example.bankserversystem.domain.repository.bank.BankRepository;
import com.example.bankserversystem.dto.bank.BankResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BankServiceImpl implements BankService{
    private final BankRepository bankRepository;
    public List<BankResponse> getNearBank(Double lat, Double lon) {
        return null;
    }
}
