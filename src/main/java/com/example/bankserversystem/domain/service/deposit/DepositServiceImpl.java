package com.example.bankserversystem.domain.service.deposit;

import com.example.bankserversystem.domain.repository.deposit.DepositRepository;
import com.example.bankserversystem.domain.repository.userinfo.UserInfoRepository;
import com.example.bankserversystem.dto.deposit.CreateDepositRequest;
import com.example.bankserversystem.dto.deposit.DepositResponse;
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
public class DepositServiceImpl implements DepositService{

    private final DepositRepository depositRepository;
    private final UserInfoRepository userInfoRepository;

    @Transactional
    public List<DepositResponse> getAllDepositData() {
        return depositRepository.findAll()
                .stream().map(DepositResponse::makeResponseFromEntity)
                .collect(Collectors.toList());
    }
    @Transactional
    public DepositResponse createDeposit(CreateDepositRequest createDeposit) {
        Deposit deposit = createDepositFromCreateDeposit(createDeposit);
        return DepositResponse.makeResponseFromEntity(depositRepository.save(deposit));
    }

    private Deposit createDepositFromCreateDeposit(CreateDepositRequest createDeposit) {
        return Deposit.builder()
                .depositName(createDeposit.getDepositName())
                .interestRate(createDeposit.getInterestRate())
                .depositCreditCondition(createDeposit.getDepositCreditCondition())
                .tag(createDeposit.getTag())
                .build();
    }

}
