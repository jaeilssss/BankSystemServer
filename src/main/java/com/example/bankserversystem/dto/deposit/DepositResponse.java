package com.example.bankserversystem.dto.deposit;

import com.example.bankserversystem.dto.TransferResponse;
import com.example.bankserversystem.entity.deposit.Deposit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Builder
@AllArgsConstructor
@Getter
@Setter
@Slf4j
public class DepositResponse {

    private final Long depositId;

    private final String depositName;

    private final Double interestRate;

    private final String depositCreditCondition;

    private final String tag;

    public static DepositResponse makeResponseFromEntity(Deposit deposit) {
        return DepositResponse.builder()
                .depositId(deposit.getDepositId())
                .depositName(deposit.getDepositName())
                .interestRate(deposit.getInterestRate())
                .depositCreditCondition(deposit.getDepositCreditCondition())
                .tag(deposit.getTag())
                .build();
    }
}
