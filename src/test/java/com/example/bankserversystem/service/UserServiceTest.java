package com.example.bankserversystem.service;

import com.example.bankserversystem.domain.repository.userinfo.DeleteUserInfoRepository;
import com.example.bankserversystem.domain.repository.userinfo.UserInfoRepository;
import com.example.bankserversystem.domain.service.userinfo.UserInfoServiceImpl;
import com.example.bankserversystem.entity.user.UserInfo;
import com.example.bankserversystem.enums.UserInfoCode;
import com.example.bankserversystem.globals.exception.MyException;
import com.example.bankserversystem.jwt.JwtProviders;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserInfoRepository userInfoRepository;

    @Mock
    private DeleteUserInfoRepository deleteUserInfoRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtProviders jwtProviders;

    @InjectMocks
    private UserInfoServiceImpl userInfoService;

    private UserInfo userInfo;

    @BeforeEach
    public void setUp() {
        userInfo = UserInfo.builder()
                .userId(1L)
                .email("test@example.com")
                .password("encodedPassword")
                .address("Test Address")
                .phoneNumber("123-456-7890")
                .birthDate(LocalDate.of(2000,1,1))
                .creditRating("Basic")
                .build();
    }
    @Test
    @DisplayName("로그인 성공 테스트")
    public void testLoginSuccess() {
        given(userInfoRepository.findByEmail(userInfo.getEmail())).willReturn(Optional.of(userInfo));
        given(passwordEncoder.matches("rawPassword", userInfo.getPassword())).willReturn(true);
        given(jwtProviders.createToken(userInfo.getEmail(), userInfo.getUserId())).willReturn("token");

        String token = userInfoService.login(userInfo.getEmail(), "rawPassword");

        assertEquals("token",token);
        verify(userInfoRepository, times(1)).findByEmail(userInfo.getEmail());
        verify(passwordEncoder, times(1)).matches("rawPassword", userInfo.getPassword());
        verify(jwtProviders, times(1)).createToken(userInfo.getEmail(), userInfo.getUserId());

    }

    @Test
    @DisplayName("로그인 실패 테스트(비밀번호 불 일치)")
    public void testLoginFailByNotMatchPassword() {
        given(userInfoRepository.findByEmail(userInfo.getEmail())).willReturn(Optional.of(userInfo));
        given(passwordEncoder.matches("wrongPassword", userInfo.getPassword())).willReturn(false);

        // When: 잘못된 패스워드로 로그인 시도
        MyException exception = assertThrows(MyException.class, () -> {
            userInfoService.login(userInfo.getEmail(), "wrongPassword");
        });

//        System.out.println(exception.getExceptionCode());
        assertEquals(UserInfoCode.INVALID_REQUEST.getCode(), exception.getExceptionCode());
    }

    @Test
    @DisplayName("로그인 실패 테스트(존재 하지 않는 회원")
    public void testLoginFailByEmptyUser() {
//        given(userInfoRepository.findByEmail(userInfo.getEmail())).willReturn(Optional.ofNullable(null));
        given(userInfoRepository.findByEmail(userInfo.getEmail())).willReturn(Optional.empty());

        MyException exception = assertThrows(MyException.class, () -> {
            userInfoService.login(userInfo.getEmail(), "wrongPassword");
        });

        assertEquals(UserInfoCode.NO_USER_INFO.getCode(), exception.getExceptionCode());
    }



}
