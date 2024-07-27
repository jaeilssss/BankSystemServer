package com.example.bankserversystem.dto.account;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AccountRequest {

    private Long depositId;
    private Long userId;

}
