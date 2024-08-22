package com.example.bankserversystem.domain.service.card;

import com.example.bankserversystem.domain.logic.CardNumberMaker;
import com.example.bankserversystem.domain.repository.account.AccountRepository;
import com.example.bankserversystem.domain.repository.card.CardRepository;
import com.example.bankserversystem.domain.repository.userinfo.UserInfoRepository;
import com.example.bankserversystem.dto.card.CardResponse;
import com.example.bankserversystem.dto.card.ChargeRequest;
import com.example.bankserversystem.dto.card.CreateCardRequest;
import com.example.bankserversystem.dto.card.CreateCardResponse;
import com.example.bankserversystem.entity.account.Account;
import com.example.bankserversystem.entity.card.Card;
import com.example.bankserversystem.entity.user.UserInfo;
import com.example.bankserversystem.enums.AccountCode;
import com.example.bankserversystem.enums.CardErrorCode;
import com.example.bankserversystem.enums.UserInfoCode;
import com.example.bankserversystem.globals.exception.MyException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CardServiceImpl implements CardService{
    private final CardRepository cardRepository;
    private final UserInfoRepository userInfoRepository;
    private final CardNumberMaker cardNumberMaker;
    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    private final String MY_COMPANY_NAME = "jaeil";

    @Transactional
    public CreateCardResponse createCardResponse(CreateCardRequest cardRequest) {
        String cardNumber = cardNumberMaker.createCardNumber();
        cardRequest.setPassword(encodePassword(cardRequest.getPassword()));
        Card card = cardRepository.save(cardRequest.toEntity(
                cardRequest, getUserInfo(cardRequest.getUserId()),
                getAccountInfo(cardRequest.getAccountNumber()), cardNumber
                ));
        return card.createCardResponse();
    }
    public List<CardResponse> getCardList(Long userId) {
        return cardRepository.findByUserInfo(getUserInfo(userId))
                .stream()
                .map(Card::toCardResponse)
                .collect(Collectors.toList());
    }

    public UserInfo getUserInfo(Long userId) {
        return userInfoRepository.findById(userId)
                .orElseThrow(() -> new MyException(
                        UserInfoCode.NO_USER_INFO.getCode(),
                        UserInfoCode.NO_USER_INFO.getMessage()));
    }

    public Account getAccountInfo(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new MyException(
                        AccountCode.NOT_FOUND_ACCOUNT_NUMBER.getCode(),
                        AccountCode.NOT_FOUND_ACCOUNT_NUMBER.getMessage()
                ));
    }

    @Transactional
    public void charge(ChargeRequest chargeRequest) {
        validateCardCompany(chargeRequest.getCardCompany());
        Card card = getCardInfo(chargeRequest.getCardNumber());

        validateExpiration(card.getExpirationDate());
        matchPassword(card.getPassword(), chargeRequest.getPassword());
        validateAccountTotalMoney(card.getAccount(), chargeRequest.getCharge());

        card.getAccount().setTotalDeposit(card.getAccount().getTotalDeposit() - chargeRequest.getCharge());
    }

    public void validateAccountTotalMoney(Account account, int charge) {
        if(account.getTotalDeposit() < charge)
            throw new MyException(
                    CardErrorCode.NOT_ENOUGH_MONEY.getCode(),
                    CardErrorCode.NOT_ENOUGH_MONEY.getMessage());

    }

    public void validateExpiration(LocalDate cardExpirationDate) {
        if(cardExpirationDate.isBefore(LocalDate.now()))
            throw new MyException(
                    CardErrorCode.INVALIDATE_EXPIRATION.getCode(),
                    CardErrorCode.INVALIDATE_EXPIRATION.getMessage()
            );
    }

    public void validateCardCompany(String cardCompany) {
        if(!cardCompany.equals(MY_COMPANY_NAME))
            throw new MyException(
                    CardErrorCode.NO_PROVIDE_OTHER_COMPANY.getCode(),
                    CardErrorCode.NO_PROVIDE_OTHER_COMPANY.getMessage());
    }
    public Card getCardInfo(String cardNumber) {
        return cardRepository.findByCardNumber(cardNumber).get();
    }

    public void matchPassword(String encodedPassword, String password) {
        if(!passwordEncoder.matches(password, encodedPassword))
            throw new MyException(
                    CardErrorCode.NOT_MATCH_PASSWORD.getCode(),
                    CardErrorCode.NOT_MATCH_PASSWORD.getMessage());
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
