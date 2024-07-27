package com.example.bankserversystem.dto.card;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@RequiredArgsConstructor
@Getter @Setter
public class CreateCardResponse {

    private String userName;
    private int cardNumber;
    private LocalDate expirationDate;
    private String accountNumber;
    private String type;
}
