package com.example.bankserversystem.dto.account;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DeleteAccountRequest {

    private Long accountId;
    private Long userId;
    private String password;
    private final String accountPassword;

}
