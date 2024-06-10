package com.example.bankserversystem.domain.service;

import com.example.bankserversystem.domain.repository.DepositRepository;
import com.example.bankserversystem.domain.repository.UserInfoRepository;
import com.example.bankserversystem.dto.Response;
import com.example.bankserversystem.dto.deposit.CreateDeposit;
import com.example.bankserversystem.dto.deposit.DepositResponse;
import com.example.bankserversystem.dto.deposit.SignUpDepositRequest;
import com.example.bankserversystem.entity.deposit.Deposit;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service
public class DepositService {

    private final DepositRepository depositRepository;
    private final UserInfoRepository userInfoRepository;

    @Transactional
    public List<DepositResponse> getAllDepositData() {
        return depositRepository.findAll()
                .stream().map(DepositResponse::makeResponseFromEntity)
                .collect(Collectors.toList());
    }
    @Transactional
    public DepositResponse createDeposit(CreateDeposit createDeposit) {
        Deposit deposit = createDepositFromCreateDeposit(createDeposit);
        return DepositResponse.makeResponseFromEntity(depositRepository.save(deposit));
    }

    private Deposit createDepositFromCreateDeposit(CreateDeposit createDeposit) {
        return Deposit.builder()
                .depositName(createDeposit.getDepositName())

                .build();
    }

}
