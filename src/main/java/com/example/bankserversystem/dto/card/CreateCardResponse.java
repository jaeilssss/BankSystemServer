package com.example.bankserversystem.dto.card;

import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CreateCardResponse {

    private String userName;
    private int cardNumber;
    private LocalDate expirationDate;
    private String accountNumber;
    private String type;
}
