package com.example.bankserversystem.dto.user;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class UserInfoRequest {

    private String email;

    private String password;

    private LocalDate birthDate;

    private String address;

    private String phoneNumber;



}
