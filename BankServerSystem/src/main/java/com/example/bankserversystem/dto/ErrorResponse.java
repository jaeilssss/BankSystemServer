package com.example.bankserversystem.dto;

import com.example.bankserversystem.enums.UserInfoCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private final String code;
    private final String message;
}
