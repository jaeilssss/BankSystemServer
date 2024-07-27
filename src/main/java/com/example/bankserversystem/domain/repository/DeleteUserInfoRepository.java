package com.example.bankserversystem.domain.repository;

import com.example.bankserversystem.entity.user.DeleteUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeleteUserInfoRepository extends JpaRepository<DeleteUserInfo, Long> {
}
