package com.example.bankserversystem.entity.card;

import com.example.bankserversystem.dto.card.CardResponse;
import com.example.bankserversystem.dto.card.CreateCardRequest;
import com.example.bankserversystem.dto.card.CreateCardResponse;
import com.example.bankserversystem.entity.account.Account;
import com.example.bankserversystem.entity.user.UserInfo;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@EntityListeners(AuditingEntityListener.class)
@DiscriminatorColumn(name = "dtype")
public abstract class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    protected UserInfo userInfo;
    @ManyToOne(fetch = FetchType.LAZY)
    protected Account account;

    @Column(nullable = false,
            unique = true)
    protected String cardNumber;
    protected String cardCompany;
    protected String password;

    protected LocalDate expirationDate;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    abstract public CreateCardResponse createCardResponse();
    abstract public CardResponse toCardResponse();

    protected void setCard(CreateCardRequest request, UserInfo userInfo, Account account, String cardNumber) {
        this.userInfo = userInfo;
        this.account = account;
        this.cardNumber = cardNumber;
        this.password = request.getPassword();
        this.cardCompany = request.getCardCompany();
        LocalDate todayLocalDate = LocalDate.now();
        this.expirationDate = todayLocalDate.plusYears(5);
    }
}
