package com.example.bankserversystem.domain.repository.account;

import com.example.bankserversystem.entity.account.DeleteAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeleteAccountRepository extends JpaRepository<DeleteAccount, Long> {
}
