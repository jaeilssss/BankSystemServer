package com.example.bankserversystem.domain.controller;

import com.example.bankserversystem.domain.logic.JWTAndUserIdChecker;
import com.example.bankserversystem.domain.service.card.CardServiceImpl;
import com.example.bankserversystem.dto.Response;
import com.example.bankserversystem.dto.card.CardResponse;
import com.example.bankserversystem.dto.card.ChargeRequest;
import com.example.bankserversystem.dto.card.CreateCardRequest;
import com.example.bankserversystem.dto.card.CreateCardResponse;
import com.example.bankserversystem.enums.APIResponseCode;
import com.example.bankserversystem.globals.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/card")
public class CardController extends BaseController {
    private final CardServiceImpl cardService;

    @Autowired
    public CardController(JWTAndUserIdChecker jwtAndUserIdChecker, CardServiceImpl cardService) {
        super(jwtAndUserIdChecker);
        this.cardService = cardService;
    }

    @PostMapping("/create/card")
    public Response<CreateCardResponse> createCard(@RequestBody CreateCardRequest createCardRequest) {
        jwtAndUserIdCheck(createCardRequest.getUserId());
        return new Response<>(
                APIResponseCode.OK,
                APIResponseCode.OK_MESSAGE,
                cardService.createCardResponse(createCardRequest)
        );
    }
    @GetMapping("/get/mycard/{userId}")
    public Response<List<CardResponse>> getMyCardList(@PathVariable("userId") Long userId) {
        jwtAndUserIdCheck(userId);
        return new Response<>(
                APIResponseCode.OK,
                APIResponseCode.OK_MESSAGE,
                cardService.getCardList(userId)
        );
    }

    @PostMapping("/charge/{userId}")
    public Response<Void> charge(@PathVariable("userId") Long userId, @RequestBody ChargeRequest chargeRequest) {
        jwtAndUserIdCheck(userId);
        cardService.charge(chargeRequest);
        return new Response<>(
                APIResponseCode.OK,
                APIResponseCode.OK_MESSAGE,
                null
        );
    }

}
