package com.example.bankserversystem.domain.service.account;

import com.example.bankserversystem.dto.account.*;
import com.example.bankserversystem.entity.account.Account;
import com.example.bankserversystem.entity.account.AccountHistory;
import com.example.bankserversystem.entity.deposit.Deposit;
import com.example.bankserversystem.entity.user.UserInfo;

import java.util.List;

public interface AccountService {
    public List<AccountResponse> getMyAccountList(Long userId);
    public void deleteAccount(DeleteAccountRequest deleteAccountRequest);
    public AccountMoneyResponse deposit(AccountMoneyRequest accountMoneyRequest);
    public AccountMoneyResponse withdraw(AccountMoneyRequest accountMoneyRequest);
    public AccountHistory saveAccountHistory(AccountHistory accountHistory);
    public Account getAccountByAccountNumber(String accountNumber);
    public Account getAccountByAccountId(Long accountId);
    public UserInfo getUserInfo(Long userId);
    public Deposit getDeposit(Long depositId);
    public void createAccount(CreateAccountRequest createAccountRequest);
    public String createAccountNumber();
    public boolean checkAccountNumber(String accountNumber);
}
