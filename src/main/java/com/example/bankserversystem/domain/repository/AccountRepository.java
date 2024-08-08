package com.example.bankserversystem.domain.repository;


import com.example.bankserversystem.entity.account.Account;
import com.example.bankserversystem.entity.user.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("select a from Account a" +
            " join fetch a.deposit ")
    List<Account> findByUserInfo(UserInfo userInfo);
    @Query("select accountNumber from Account order by createdAt desc limit 1")
    Optional<String>findAccountNumberDescLimitFirst();

    Optional<Account> findByAccountNumber(String accountNumber);

    boolean existsAccountByAccountNumber(String accountNumber);
}
