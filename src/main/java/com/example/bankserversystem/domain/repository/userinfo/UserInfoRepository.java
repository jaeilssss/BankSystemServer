package com.example.bankserversystem.domain.repository.userinfo;

import com.example.bankserversystem.entity.user.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByEmail(String email);

    Optional<UserInfo> findByEmailAndPassword(String email, String password);
}
