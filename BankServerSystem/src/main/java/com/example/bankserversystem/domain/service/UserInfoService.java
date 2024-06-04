package com.example.bankserversystem.domain.service;

import com.example.bankserversystem.domain.repository.DeleteUserInfoRepository;
import com.example.bankserversystem.domain.repository.UserInfoRepository;
import com.example.bankserversystem.dto.user.UserInfoRequest;
import com.example.bankserversystem.dto.user.UserInfoResponse;
import com.example.bankserversystem.entity.user.DeleteUserInfo;
import com.example.bankserversystem.entity.user.UserInfo;
import com.example.bankserversystem.enums.UserInfoCode;
import com.example.bankserversystem.exception.user.UserInfoException;
import com.example.bankserversystem.jwt.JwtProviders;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final DeleteUserInfoRepository deleteUserInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProviders jwtProviders;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    @Transactional
    public String login(String email, String password) {
        UserInfo userInfo = findByEmail(email);

        if (isPasswordMatch(password, userInfo.getPassword())) {
            return jwtProviders.createToken(email, userInfo.getUserId());
        } else {
            throw new UserInfoException(UserInfoCode.INVALID_REQUEST);
        }
    }
    @Transactional
    public UserInfoResponse signUp(UserInfoRequest userInfoRequest) {
        //이미 가입된 이메일이 있을 경우 UserInfoException 발생
        validateUserEmail(userInfoRequest.getEmail());
        String passwordEncode = passwordEncoder.encode(userInfoRequest.getPassword());

        UserInfo createdUserInfo = createEntityFromRequest(userInfoRequest);
        createdUserInfo.setPassword(passwordEncode);
        return UserInfoResponse.getUserResponseFromUserInfo(userInfoRepository.save(createdUserInfo));
    }

    @Transactional
    public UserInfoResponse getUserInfoDetail(Long userId) {
        return UserInfoResponse.getUserResponseFromUserInfo(getUserInfo(userId));
    }

    @Transactional
    public UserInfoResponse modifyUserInfo(Long userId, UserInfoRequest userInfoRequest) {
        UserInfo userInfo = getUserInfo(userId);

        userInfo.setAddress(userInfo.getAddress());
        userInfo.setBirthDate(userInfoRequest.getBirthDate());
        userInfo.setPhoneNumber(userInfo.getPhoneNumber());
        userInfo.setPhoneNumber(userInfo.getPhoneNumber());

        return UserInfoResponse.getUserResponseFromUserInfo(userInfo);
    }


    private UserInfo findByEmail(String email) {
        return userInfoRepository.findByEmail(email)
                .orElseThrow(() -> new UserInfoException(UserInfoCode.NO_USER_INFO));
    }
    private UserInfo getUserInfo(Long userId) {
        return  userInfoRepository.findById(userId)
                .orElseThrow(() -> new UserInfoException(UserInfoCode.NO_USER_INFO));
    }

    @Transactional
    public void deleteUserInfo(Long userId) {
        // DeleteUserInfo Table에 해당 유저 데이터 저장
        DeleteUserInfo deleteUserInfo = DeleteUserInfo.deleteUserInfoFromUserInfo(getUserInfo(userId));
        deleteUserInfoRepository.save(deleteUserInfo);

        //그리고 현재 UserInfo 테이블에 유저 정보 삭제
        userInfoRepository.delete(getUserInfo(userId));
    }

    public void validateUserEmail(String email) {
        userInfoRepository.findByEmail(email).ifPresent(
                (userInfo -> {throw new UserInfoException(UserInfoCode.DUPLICATED_EMAIL);}));
    }

    public boolean isPasswordMatch(String password, String encodedPassword) {
        return passwordEncoder.matches(password, encodedPassword);
    }

    public UserInfo createEntityFromRequest(UserInfoRequest userInfoRequest) {
        return UserInfo.builder()
                .email(userInfoRequest.getEmail())
                .password(userInfoRequest.getPassword())
                .birthDate(userInfoRequest.getBirthDate())
                .creditRating("Basic")
                .address(userInfoRequest.getAddress())
                .phoneNumber(userInfoRequest.getPhoneNumber())
                .build();
    }
}
