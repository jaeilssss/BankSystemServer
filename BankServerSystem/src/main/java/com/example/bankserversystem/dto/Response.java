package com.example.bankserversystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Response<T> {
    private String code;
    private String message;
    private T data;

    public Response(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Response(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
