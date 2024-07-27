package com.example.bankserversystem.entity.account;


import com.example.bankserversystem.entity.deposit.Deposit;
import com.example.bankserversystem.entity.user.UserInfo;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.userdetails.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name="account")
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
    private String accountPassword;

    @Column(nullable = false)
    private String accountType;

    private int totalDeposit;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

}
