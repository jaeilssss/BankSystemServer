package com.example.bankserversystem.dto.card;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class ChargeRequest {

    private String cardNumber;
    private String cardCompany;
    private String password;
    private int charge;
}
