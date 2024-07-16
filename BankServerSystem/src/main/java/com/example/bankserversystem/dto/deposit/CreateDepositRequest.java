package com.example.bankserversystem.dto.deposit;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CreateDepositRequest {

    private final String depositName;

    private final Double interestRate;

    private final String depositCreditCondition;

    private final String tag;


}
