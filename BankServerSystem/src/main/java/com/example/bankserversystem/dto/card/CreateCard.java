package com.example.bankserversystem.dto.card;

import com.example.bankserversystem.entity.card.Card;
import lombok.Builder;
import lombok.Getter;


@Getter
public class CreateCard {
    private Long userId;
    private String accountId;
    private String cardCompany;
    private String password;
}
