package com.example.banksystembatch.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private UserInfo userInfo;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Deposit deposit;

    @Column(nullable = false)
    private String accountNumber;
    @Column(nullable = false)
    private String accountType;

    private int totalDeposit;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public AccountHistory createAccountHistoryForInterest(int interest) {
        return AccountHistory.builder()
                .type("이자입금")
                .historyMessage(
                        totalDeposit + "에서" +"이자율 : " + deposit.getInterestRate() +
                                "%로 " + interest + "원의 이자가 입금되었습니다."
                )
                .accountNumber(accountNumber)
                .build();
    }
}
