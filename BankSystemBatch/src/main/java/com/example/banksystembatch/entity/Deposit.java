package com.example.banksystembatch.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long depositId;

    @Column(nullable = false)
    private String depositName;

    @Column(nullable = false)
    private Double interestRate;

    @Column(nullable = false)
    private String depositCreditCondition;

    private String tag;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


}
