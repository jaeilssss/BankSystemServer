package com.example.bankserversystem.domain.repository;

import com.example.bankserversystem.entity.card.Card;
import com.example.bankserversystem.entity.user.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByUserInfo(UserInfo userInfo);
    Optional<Card> findByCardNumber(int cardNumber);
}
