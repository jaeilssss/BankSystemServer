package com.example.bankserversystem.entity.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table
public class DeleteUserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long userId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column
    private String creditRating;

    @Column
    private String address;

    @Column
    private String phoneNumber;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime deleteDate;

    public static DeleteUserInfo deleteUserInfoFromUserInfo(UserInfo userInfo) {
        return DeleteUserInfo.builder()
                .email(userInfo.getEmail())
                .password(userInfo.getPassword())
                .birthDate(userInfo.getBirthDate())
                .creditRating(userInfo.getCreditRating())
                .address(userInfo.getAddress())
                .phoneNumber(userInfo.getPhoneNumber())
                .build();
    }
}
