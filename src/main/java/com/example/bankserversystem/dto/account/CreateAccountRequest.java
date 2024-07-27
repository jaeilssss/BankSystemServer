package com.example.bankserversystem.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class CreateAccountRequest {
    private final Long depositId;
    private final Long userId;
    private final String accountPassword;
}
