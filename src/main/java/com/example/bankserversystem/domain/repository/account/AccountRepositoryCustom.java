package com.example.bankserversystem.domain.repository.account;

import com.example.bankserversystem.entity.account.Account;
import com.example.bankserversystem.entity.user.UserInfo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepositoryCustom {

    List<Account> findByUserInfo(UserInfo userInfo);
    Optional<String> findAccountNumberDescLimitFirst();

    Optional<Account> findByAccountNumber(String accountNumber);

    boolean existsAccountByAccountNumber(String accountNumber);
}
