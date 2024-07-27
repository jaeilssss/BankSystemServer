package com.example.bankserversystem.domain.service;

import com.example.bankserversystem.domain.repository.*;
import com.example.bankserversystem.dto.account.AccountResponse;
import com.example.bankserversystem.dto.account.DeleteAccountRequest;
import com.example.bankserversystem.dto.account.AccountMoneyRequest;
import com.example.bankserversystem.dto.account.AccountMoneyResponse;
import com.example.bankserversystem.entity.account.Account;
import com.example.bankserversystem.entity.account.AccountHistory;
import com.example.bankserversystem.entity.account.DeleteAccount;
import com.example.bankserversystem.entity.deposit.Deposit;
import com.example.bankserversystem.entity.user.UserInfo;
import com.example.bankserversystem.enums.AccountCode;
import com.example.bankserversystem.enums.DepositCode;
import com.example.bankserversystem.enums.UserInfoCode;
import com.example.bankserversystem.exception.account.AccountException;
import com.example.bankserversystem.exception.deposit.DepositException;
import com.example.bankserversystem.exception.user.UserInfoException;
import com.example.bankserversystem.globals.exception.MyException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final static String JAEIL_BANK_CODE = "109";
    private final AccountRepository accountRepository;
    private final UserInfoRepository userInfoRepository;
    private final DepositRepository depositRepository;
    private final DeleteAccountRepository deleteAccountRepository;
    private final AccountHistoryRepository accountHistoryRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public List<AccountResponse> getMyAccountList(Long userId) {
        return accountRepository.findByUserInfo(getUserInfo(userId))
                        .stream().map(AccountResponse::makeAccountResponseFromAccount)
                        .collect(Collectors.toList());
    }
    @Transactional
    public void deleteAccount(DeleteAccountRequest deleteAccountRequest) {
        if(!matchPassword(
                getUserInfo(deleteAccountRequest.getUserId()),
                deleteAccountRequest.getPassword()
        )) throw new AccountException(AccountCode.INVALID_REQUEST);

        Account account = getAccount(deleteAccountRequest.getAccountId());
        accountRepository.delete(account);
        deleteAccountRepository.save(makeDeleteAccount(deleteAccountRequest, account.getAccountNumber()));
    }

    @Transactional
    public AccountMoneyResponse deposit(AccountMoneyRequest accountMoneyRequest) {
        Account account = getAccountByAccountNumber(accountMoneyRequest.getAccountNumber());
        account.setTotalDeposit(account.getTotalDeposit() + accountMoneyRequest.getDepositMoney());
        AccountHistory accountHistory = saveAccountHistory(makeAccountHistoryFromDeposit(accountMoneyRequest));

        return AccountMoneyResponse.makeDepositAccountResponse(
                account.getAccountNumber(),
                accountHistory.getHistoryMessage(),
                accountHistory.getCreatedAt());
    }

    @Transactional
    public AccountMoneyResponse withdraw(AccountMoneyRequest accountMoneyRequest) {
        Account account = getAccountByAccountNumber(accountMoneyRequest.getAccountNumber());
        if(account.getTotalDeposit() < accountMoneyRequest.getDepositMoney()) {
            throw new AccountException(AccountCode.LACK_OF_DEPOSIT);
        }

        account.setTotalDeposit(account.getTotalDeposit() - accountMoneyRequest.getDepositMoney());
        AccountHistory accountHistory = saveAccountHistory(makeAccountHistoryFromWithdraw(accountMoneyRequest));

        return AccountMoneyResponse.makeDepositAccountResponse(
                account.getAccountNumber(),
                accountHistory.getHistoryMessage(),
                accountHistory.getCreatedAt());
    }
    @Transactional
    public AccountHistory saveAccountHistory(AccountHistory accountHistory) {
        return accountHistoryRepository.save(accountHistory);
    }

    @Transactional
    public Account getAccountByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new MyException(
                        AccountCode.NOT_FOUND_ACCOUNT_NUMBER.getCode(),
                        AccountCode.NOT_FOUND_ACCOUNT_NUMBER.getMessage()));
    }
    @Transactional
    public Account getAccount(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new MyException(
                        AccountCode.NOT_FOUND_ACCOUNT_NUMBER.getCode(),
                        AccountCode.NOT_FOUND_ACCOUNT_NUMBER.getMessage()));
    }
    @Transactional
    public UserInfo getUserInfo(Long userId) {
        return userInfoRepository.findById(userId)
                .orElseThrow(() -> new UserInfoException(UserInfoCode.NO_USER_INFO));
    }

    @Transactional
    public Deposit getDeposit(Long depositId) {
        return depositRepository.findById(depositId)
                .orElseThrow(() -> new DepositException(DepositCode.NO_DEPOSIT_DATA));
    }

    public boolean matchPassword(UserInfo userInfo, String password) {
        return passwordEncoder.matches(password, userInfo.getPassword());
    }
    @Transactional
    public void createAccount(Long depositId, Long userId) {
        Deposit deposit = getDeposit(depositId);
        UserInfo userInfo = getUserInfo(userId);
        String accountNumber = createAccountNumber();
        int count = 0;
        while (count < 10) {
            if(!checkAccountNumber(accountNumber)) break;
            accountNumber = createAccountNumber();
            count++;
            if(count==10) throw new AccountException(AccountCode.CRETE_ACCOUNT_ERROR);
        }
        accountRepository.save(makeAccount(userInfo, deposit, accountNumber));
    }

    @Transactional
    public String createAccountNumber() {
        StringBuilder newAccountNumber = new StringBuilder();
        newAccountNumber.append(JAEIL_BANK_CODE);
        newAccountNumber.append(makeAccountBackNumber());
        return newAccountNumber.toString();
    }

    public String makeAccountBackNumber() {
        return String.valueOf(getCurrentDateTimeToLong() + (long) (Math.random()*100000000000L));
    }

    public Long getCurrentDateTimeToLong() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        return Long.parseLong(localDateTime.format(formatter));
    }
    @Transactional
    public boolean checkAccountNumber(String accountNumber) {
        return accountRepository.existsAccountByAccountNumber(accountNumber);
    }

    public Account makeAccount(UserInfo userInfo, Deposit deposit, String accountNumber) {
        return Account.builder()
                .userInfo(userInfo)
                .deposit(deposit)
                .accountNumber(accountNumber)
                .accountType("예금")
                .totalDeposit(0)
                .build();
    }

    public DeleteAccount makeDeleteAccount(
            DeleteAccountRequest deleteAccountRequest,
            String accountNumber) {

        return DeleteAccount.builder()
                .accountId(deleteAccountRequest.getAccountId())
                .userId(deleteAccountRequest.getUserId())
                .accountNumber(accountNumber)
                .build();
    }

    public AccountHistory makeAccountHistoryFromDeposit(AccountMoneyRequest request) {
        return AccountHistory.builder()
                .historyMessage(
                        request.getAccountNumber()+"으로 "
                        +request.getDepositMoney()+"원을 입금 됐습니다.")
                .accountNumber(request.getAccountNumber())
                .type("입금")
                .build();
    }
    public AccountHistory makeAccountHistoryFromWithdraw(AccountMoneyRequest request) {
        return AccountHistory.builder()
                .historyMessage(
                        request.getAccountNumber()+"에서 "
                                +request.getDepositMoney()+"원을 출금 됐습니다.")
                .accountNumber(request.getAccountNumber())
                .type("출금")
                .build();
    }
}
