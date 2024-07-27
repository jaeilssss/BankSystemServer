package com.example.bankserversystem.dto.card;

import com.example.bankserversystem.entity.account.Account;
import com.example.bankserversystem.entity.card.Card;
import com.example.bankserversystem.entity.card.DebitCard;
import com.example.bankserversystem.entity.user.UserInfo;
import com.example.bankserversystem.enums.CardErrorCode;
import com.example.bankserversystem.globals.exception.MyException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter @Setter
public class CreateCardRequest {

    private Long userId;
    private String accountId;
    private String cardCompany;
    private String password;
    private String type;
    private String accountNumber;
    private String accountPassword;
    public Card toEntity(CreateCardRequest request, UserInfo userInfo, Account account, int accountNumber) {
        if(type.equals("DebitCard")) {
           DebitCard debitCard = new DebitCard();
           debitCard.setDebitCard(request, userInfo, account, accountNumber);
           return debitCard;
        }
        throw new MyException(
                CardErrorCode.ERROR.getCode(),
                CardErrorCode.CREATE_CARD_ERROR.getMessage()
        );
    }
}
