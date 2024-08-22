package com.example.bankserversystem.domain.repository.account;

import com.example.bankserversystem.entity.account.Account;
import com.example.bankserversystem.entity.account.QAccount;
import com.example.bankserversystem.entity.deposit.QDeposit;
import com.example.bankserversystem.entity.user.QUserInfo;
import com.example.bankserversystem.entity.user.UserInfo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepositoryCustomImpl implements AccountRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    QAccount qAccount = QAccount.account;
    QUserInfo qUserInfo = QUserInfo.userInfo;
    QDeposit qDeposit = QDeposit.deposit;
    public AccountRepositoryCustomImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Account> findByUserInfo(UserInfo userInfo) {
        return jpaQueryFactory.selectFrom(qAccount)
                .join(qAccount.deposit, qDeposit).fetchJoin()
                .join(qAccount.userInfo, qUserInfo).fetchJoin()
                .fetch();
    }

    @Override
    public Optional<String> findAccountNumberDescLimitFirst() {
        return Optional.ofNullable(jpaQueryFactory.select(qAccount.accountNumber)
                .orderBy(qAccount.accountNumber.desc())
                .limit(1)
                .fetchOne());
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(qAccount)
                .where(qAccount.accountNumber.eq(accountNumber)).fetchOne());
    }

    @Override
    public boolean existsAccountByAccountNumber(String accountNumber) {
        return jpaQueryFactory.selectOne()
                .from(qAccount)
                .where(qAccount.accountNumber.eq(accountNumber))
                .fetchFirst() != null;
    }
}
