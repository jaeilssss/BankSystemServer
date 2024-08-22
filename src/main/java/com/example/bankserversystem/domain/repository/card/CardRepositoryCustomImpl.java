package com.example.bankserversystem.domain.repository.card;

import com.example.bankserversystem.entity.account.QAccount;
import com.example.bankserversystem.entity.card.Card;
import com.example.bankserversystem.entity.card.QCard;
import com.example.bankserversystem.entity.user.QUserInfo;
import com.example.bankserversystem.entity.user.UserInfo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CardRepositoryCustomImpl implements CardRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    QCard qCard = QCard.card;
    QUserInfo qUserInfo = QUserInfo.userInfo;
    QAccount qAccount = QAccount.account;

    public CardRepositoryCustomImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Card> findByUserInfo(UserInfo userInfo) {
        return jpaQueryFactory.selectFrom(qCard)
                .join(qCard.userInfo, qUserInfo).fetchJoin()
                .join(qCard.account, qAccount).fetchJoin()
                .where(qCard.userInfo.eq(qUserInfo))
                .fetch();
    }

    @Override
    public Optional<Card> findByCardNumber(String cardNumber) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(qCard)
                .join(qCard.userInfo, qUserInfo).fetchJoin()
                .join(qCard.account, qAccount).fetchJoin()
                .where(qCard.cardNumber.eq(cardNumber))
                .fetchOne());
    }
}
