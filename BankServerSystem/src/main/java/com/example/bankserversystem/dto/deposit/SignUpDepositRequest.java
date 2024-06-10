package com.example.bankserversystem.dto.deposit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Getter
@Setter
@Builder
public class SignUpDepositRequest {
    
    public Long userId;
    public Long depositId;
    public int expireYear;
    public String privateNumber;

}
