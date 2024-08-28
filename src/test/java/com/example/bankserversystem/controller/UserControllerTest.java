package com.example.bankserversystem.controller;

import com.example.bankserversystem.config.JpaConfig;
import com.example.bankserversystem.config.SecurityConfig;
import com.example.bankserversystem.domain.controller.UserController;
import com.example.bankserversystem.domain.logic.JWTAndUserIdChecker;
import com.example.bankserversystem.domain.service.userinfo.UserInfoService;
import com.example.bankserversystem.dto.Response;
import com.example.bankserversystem.dto.user.LoginRequest;
import com.example.bankserversystem.dto.user.UserInfoRequest;
import com.example.bankserversystem.dto.user.UserInfoResponse;
import com.example.bankserversystem.enums.APIResponseCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@Import(JpaConfig.class)  // 필요한 설정 클래스만 임포트
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserInfoService userInfoService;
    @MockBean
    private JWTAndUserIdChecker jwtAndUserIdChecker;
    private String jwtToken = "mocked-jwt-token";

//    @BeforeEach
//    public void setUp() {
//        // Mocking the Authentication and SecurityContext
//        Authentication authentication = mock(Authentication.class);
//        SecurityContext securityContext = mock(SecurityContext.class);
//
//        // Mocking the getPrincipal method to return a specific userId
//        Long mockUserId = 1L;
//        when(authentication.getPrincipal()).thenReturn(mockUserId);
//
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//        SecurityContextHolder.setContext(securityContext);
//    }

    @Test
    @DisplayName("회원가입 테스트")
    public void testSignUp() throws Exception {
        //given
        UserInfoRequest userInfoRequest = UserInfoRequest.builder()
                .email("test10@test.com")
                .password("1234")
                .address("paju")
                .birthDate(LocalDate.of(1995,1,9))
                .phoneNumber("01030581556")
                .build();


        UserInfoResponse userInfoResponse = UserInfoResponse.builder()
                .userId(1L).email("test10@test.com")
                .birthDate(LocalDate.of(1995,1,9)).creditRating("Basic")
                .address("paju").phoneNumber("1234").build();

        Response<UserInfoResponse> expectedResponse = new Response<>(
                APIResponseCode.OK,
                APIResponseCode.OK_MESSAGE,
                userInfoResponse
        );
        given(userInfoService.signUp(any(UserInfoRequest.class))).willReturn(userInfoResponse);

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userInfoRequest)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    @DisplayName("로그인 테스트")
    public void testLogin() throws Exception {
        //given
        given(userInfoService.login(anyString(), anyString())).willReturn(jwtToken);
        LoginRequest loginRequest = LoginRequest.builder()
                .email("test@test.com")
                .password("1234")
                .build();

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(loginRequest)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(asJsonString(
                        new Response<String>(
                                APIResponseCode.OK,
                                APIResponseCode.OK_MESSAGE,
                                jwtToken
                        )
                )));
    }

    @Test
    @DisplayName("유저 정보 검색 테스트")
    public void testSearch() throws Exception {
        //given
        UserInfoResponse userInfoResponse = UserInfoResponse.builder()
                        .email("test@test.com").address("test")
                        .userId(1L).birthDate(LocalDate.of(1995,1,9))
                        .phoneNumber("1234").creditRating("Basic").build();
        given(userInfoService.getUserInfoDetail(1L)).willReturn(userInfoResponse);

        Response<UserInfoResponse> expected = new Response<>(
                APIResponseCode.OK,
                APIResponseCode.OK_MESSAGE,
                userInfoResponse
        );
        mockMvc.perform(get("/api/user/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(asJsonString(expected)));
    }
    private String asJsonString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
