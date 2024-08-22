package com.example.bankserversystem.domain.service.userinfo;

import com.example.bankserversystem.dto.user.UserInfoRequest;
import com.example.bankserversystem.dto.user.UserInfoResponse;

public interface UserInfoService {
    public String login(String email, String password);
    public UserInfoResponse signUp(UserInfoRequest userInfoRequest);
    public UserInfoResponse getUserInfoDetail(Long userId);
    public UserInfoResponse modifyUserInfo(Long userId, UserInfoRequest userInfoRequest);
    public void deleteUserInfo(Long userId);
}
