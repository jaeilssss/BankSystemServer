package com.example.bankserversystem.domain.repository.account;

import com.example.bankserversystem.entity.account.AccountHistory;
import com.example.bankserversystem.entity.account.QAccount;
import com.example.bankserversystem.entity.account.QAccountHistory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AccountHistoryRepositoryCustomImpl implements AccountHistoryRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    QAccountHistory qAccountHistory = QAccountHistory.accountHistory;
    QAccount qAccount = QAccount.account;

    public AccountHistoryRepositoryCustomImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Optional<List<AccountHistory>> findByAccountNumber(String accountNumber) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(qAccountHistory)
                        .join(qAccountHistory.account, qAccount).fetchJoin()
                .where(qAccountHistory.accountNumber.eq(accountNumber)).fetch());
    }
}
