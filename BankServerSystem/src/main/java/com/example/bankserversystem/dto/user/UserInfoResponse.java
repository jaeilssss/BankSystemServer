package com.example.bankserversystem.dto.user;

import com.example.bankserversystem.entity.user.UserInfo;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserInfoResponse {

    public Long userId;

    public String email;

    public LocalDate birthDate;

    public String creditRating;

    public String address;

    public String phoneNumber;

    public static UserInfoResponse getUserResponseFromUserInfo(UserInfo userInfo) {
        return UserInfoResponse.builder()
                .userId(userInfo.getUserId())
                .email(userInfo.getEmail())
                .birthDate(userInfo.getBirthDate())
                .creditRating(userInfo.getCreditRating())
                .address(userInfo.getAddress())
                .phoneNumber(userInfo.getPhoneNumber())
                .build();
    }

}
