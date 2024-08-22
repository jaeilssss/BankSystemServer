package com.example.bankserversystem.domain.service.card;

import com.example.bankserversystem.dto.card.ChargeRequest;
import com.example.bankserversystem.dto.card.CreateCardRequest;
import com.example.bankserversystem.dto.card.CreateCardResponse;

public interface CardService {
    public CreateCardResponse createCardResponse(CreateCardRequest cardRequest);
    public void charge(ChargeRequest chargeRequest);
}
