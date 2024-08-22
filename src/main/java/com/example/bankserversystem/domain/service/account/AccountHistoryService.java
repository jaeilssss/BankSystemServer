package com.example.bankserversystem.domain.service.account;

import com.example.bankserversystem.dto.account.history.AccountHistoryRequest;
import com.example.bankserversystem.dto.account.history.AccountHistoryResponse;

import java.util.List;

public interface AccountHistoryService {
    public List<AccountHistoryResponse> getMyAccountHistory(AccountHistoryRequest request);
}
