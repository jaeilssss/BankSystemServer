package com.example.bankserversystem.domain.service;

import com.example.bankserversystem.domain.repository.AccountRepository;
import com.example.bankserversystem.dto.account.AccountResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    @Transactional
    public List<AccountResponse> getMyAccountList(Long userId) {
        
    }
}
