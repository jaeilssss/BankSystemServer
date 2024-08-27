package com.example.bankserversystem.dto.bank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class CreateBankRequest {
    private String bankName;
    private String openTime;
    private String closeTime;
    private Double latitude;
    private Double longitude;
}
