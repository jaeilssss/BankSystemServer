package com.example.bankserversystem.domain.controller;

import com.example.bankserversystem.dto.Response;
import com.example.bankserversystem.entity.account.AccountHistory;
import com.example.bankserversystem.enums.APIResponseCode;
import com.example.bankserversystem.jwt.JwtAuthenticationFilter;
import com.example.bankserversystem.jwt.JwtProviders;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/account/history")
public class AccountHistoryController {

  @GetMapping("/test")
  public Response<List<AccountHistory>> getMyAccountHistory() {
      System.out.println("-00-0o-0-0-0-0-");
      System.out.println((Long)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
      return new Response<>(
              APIResponseCode.OK,
              APIResponseCode.OK_MESSAGE,
              null
      );
  }
}
