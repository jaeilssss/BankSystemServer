package com.example.bankserversystem.domain.repository.card;

import com.example.bankserversystem.entity.card.Card;
import com.example.bankserversystem.entity.user.UserInfo;

import java.util.List;
import java.util.Optional;

public interface CardRepositoryCustom {
    List<Card> findByUserInfo(UserInfo userInfo);
    Optional<Card> findByCardNumber(String cardNumber);
}
