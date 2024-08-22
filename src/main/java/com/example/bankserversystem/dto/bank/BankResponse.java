package com.example.bankserversystem.dto.bank;

import com.example.bankserversystem.entity.bank.Bank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankResponse {

    private Long id;
    private String bankName;
    private Double latitude;
    private Double longitude;

    public static BankResponse toResponse(Bank bank) {
        return BankResponse.builder()
                .id(bank.getId())
                .bankName(bank.getBankName())
                .latitude(bank.getLocation().getY())
                .longitude(bank.getLocation().getX())
                .build();
    }
}
