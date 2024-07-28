package com.example.bankserversystem.entity.card;

import com.example.bankserversystem.dto.card.CardResponse;
import com.example.bankserversystem.dto.card.CreateCardRequest;
import com.example.bankserversystem.dto.card.CreateCardResponse;
import com.example.bankserversystem.entity.account.Account;
import com.example.bankserversystem.entity.user.UserInfo;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Builder
@DiscriminatorValue("DebitCards")
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
public class DebitCard extends Card{

    @OneToOne(fetch = FetchType.LAZY)
    private DebitCardDetail cardDetail;

    public void test() {
        System.out.println("test");
    }
    @Override
    public CreateCardResponse createCardResponse() {
        System.out.println("Debit card");
        return CreateCardResponse.builder()
                .accountNumber(getAccount().getAccountNumber())
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

    public Card setDebitCard(CreateCardRequest request, UserInfo userInfo, Account account, String cardNumber) {
        setCard(request, userInfo, account, cardNumber);
        return this;
    }
}
