package com.example.bankserversystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Response<T> {
    private final String code;
    private final String message;
    private final T data;
}
