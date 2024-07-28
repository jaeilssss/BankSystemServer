package com.example.bankserversystem.dto.card;

import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CreateCardResponse {

    private String cardNumber;
    private LocalDate expirationDate;
    private String accountNumber;
    private String type;
}
