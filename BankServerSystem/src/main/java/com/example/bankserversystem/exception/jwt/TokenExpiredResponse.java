package com.example.bankserversystem.exception.jwt;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TokenExpiredResponse {
    public final String code;
    public final String message;
}
