package com.example.bankserversystem.exception.jwt;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.swing.text.Style;
import java.io.IOException;

@Slf4j
@Component
public class JwtExceptionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        try {
            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            setErrorResponse(HttpStatus.UNAUTHORIZED, response, e);
        }
    }

    public void setErrorResponse(HttpStatus status, HttpServletResponse res, Throwable ex) throws IOException {
        res.setStatus(status.value());
        res.setContentType("application/json; charset=UTF-8");
        res.getWriter().write(TokenExpiredResponseToJson());
    }

    public String TokenExpiredResponseToJson() {
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("code","Token-Expired");
        jsonObject.addProperty("message","토큰이 만료 되었습니다.");
        return gson.toJson(jsonObject);
    }
}


