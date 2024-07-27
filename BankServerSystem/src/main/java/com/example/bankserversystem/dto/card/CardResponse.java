package com.example.bankserversystem.dto.card;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
public class CardResponse {
    private int cardNumber;
    private LocalDate expirationDate;
    private Long creditLimit;
    private LocalDate paymentDate;
    private String type;
}
