package com.example.bankserversystem.dto.card;

import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardResponse {
    private String cardNumber;
    private LocalDate expirationDate;
//    private Long creditLimit;
//    private LocalDate paymentDate;
    private String type;
}
