package com.example.bankserversystem.domain.logic;

import com.example.bankserversystem.domain.repository.card.CardRepository;
import com.example.bankserversystem.enums.CardErrorCode;
import com.example.bankserversystem.globals.exception.MyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class CardNumberMaker {
    private final CardRepository cardRepository;
    private final String DEBIT_CARD_NUMBER = "1";


    @Transactional
    public String createCardNumber() {
        int count = 0;
        while (count < 10) {
            String cardNumber = makeCardNumber();
            if(cardRepository.findByCardNumber(cardNumber).isEmpty())
                return cardNumber;
            count++;
        }
        throw new MyException(
                CardErrorCode.CREATE_CARD_ERROR.getCode(),
                CardErrorCode.CREATE_CARD_ERROR.getMessage());
    }

    private String makeCardNumber() {
        StringBuilder newAccountNumber = new StringBuilder();
        newAccountNumber.append(DEBIT_CARD_NUMBER);
        newAccountNumber.append(makeCardBackNumber());
        return newAccountNumber.toString();
    }

    private String makeCardBackNumber() {
        return String.valueOf(getCurrentDateTimeToLong() + (long) (Math.random()*100000L));
    }
    private Long getCurrentDateTimeToLong() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        return Long.parseLong(localDateTime.format(formatter));
    }
}
