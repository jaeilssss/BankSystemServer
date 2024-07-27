package com.example.bankserversystem.entity.card;

import com.example.bankserversystem.dto.card.CardResponse;
import com.example.bankserversystem.dto.card.CreateCardRequest;
import com.example.bankserversystem.dto.card.CreateCardResponse;
import com.example.bankserversystem.entity.account.Account;
import com.example.bankserversystem.entity.user.UserInfo;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@Builder
@DiscriminatorValue("DebitCards")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class DebitCard extends Card{

    @OneToOne(fetch = FetchType.LAZY)
    private DebitCardDetail cardDetail;

    @Override
    public CreateCardResponse createCardResponse() {
        return CreateCardResponse.builder()
                .expirationDate(getExpirationDate())
                .cardNumber(getCardNumber())
                .type("DebitCards")
                .build();
    }

    @Override
    public CardResponse toCardResponse() {
        return CardResponse.builder()
                .expirationDate(getExpirationDate())
                .cardNumber(getCardNumber())
                .expirationDate(getExpirationDate())
                .type("DebitCards")
                .build();
    }

    public Card setDebitCard(CreateCardRequest request, UserInfo userInfo, Account account, int cardNumber) {
        setCard(request, userInfo, account, cardNumber);
        return this;
    }
}
