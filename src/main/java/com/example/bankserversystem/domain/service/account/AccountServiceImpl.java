package com.example.bankserversystem.domain.service.account;

import com.example.bankserversystem.domain.repository.account.AccountHistoryRepository;
import com.example.bankserversystem.domain.repository.account.AccountRepository;
import com.example.bankserversystem.domain.repository.account.DeleteAccountRepository;
import com.example.bankserversystem.domain.repository.deposit.DepositRepository;
import com.example.bankserversystem.domain.repository.userinfo.UserInfoRepository;
import com.example.bankserversystem.dto.account.*;
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
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService{
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
                getUserInfo(deleteAccountRequest.getUserId()).getPassword(),
                deleteAccountRequest.getPassword()
        )) throw new AccountException(AccountCode.INVALID_REQUEST);

        Account account = getAccountByAccountId(deleteAccountRequest.getAccountId());
        accountRepository.delete(account);
        deleteAccountRepository.save(makeDeleteAccount(deleteAccountRequest, account.getAccountNumber()));
    }

    @Transactional
    public AccountMoneyResponse deposit(AccountMoneyRequest accountMoneyRequest) {

        Account account = getAccountByAccountNumber(accountMoneyRequest.getAccountNumber());
        matchPassword(account.getAccountPassword(), accountMoneyRequest.getAccountPassword());
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
        matchPassword(account.getAccountPassword(), accountMoneyRequest.getAccountPassword());
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
    public Account getAccountByAccountId(Long accountId) {
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

    public boolean matchPassword(String encodedPassword, String password) {
        return passwordEncoder.matches(password, encodedPassword);
    }

    @Transactional
    public void createAccount(CreateAccountRequest createAccountRequest) {
        Deposit deposit = getDeposit(createAccountRequest.getDepositId());
        UserInfo userInfo = getUserInfo(createAccountRequest.getUserId());
        String accountNumber = createAccountNumber();

        int count = 0;
        while (count < 10) {
            if(!checkAccountNumber(accountNumber)) break;
            accountNumber = createAccountNumber();
            count++;
            if(count==10) throw new AccountException(AccountCode.CRETE_ACCOUNT_ERROR);
        }
        String encodePassword = passwordEncoder.encode(createAccountRequest.getAccountPassword());
        accountRepository.save(makeAccount(userInfo, deposit, accountNumber,encodePassword));
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

    public Account makeAccount(UserInfo userInfo, Deposit deposit, String accountNumber, String encodedPassword) {
        return Account.builder()
                .userInfo(userInfo)
                .deposit(deposit)
                .accountNumber(accountNumber)
                .accountType("예금")
                .accountPassword(encodedPassword)
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
