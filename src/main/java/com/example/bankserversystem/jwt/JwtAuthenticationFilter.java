package com.example.bankserversystem.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.java.Log;
import org.apache.catalina.util.StringUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

import static com.example.bankserversystem.jwt.JwtProviders.AUTHORITIES_KEY;

public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtProviders jwtProviders;

    public JwtAuthenticationFilter(JwtProviders jwtProviders) {
        this.jwtProviders = jwtProviders;
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {
            String token = resolveToken((HttpServletRequest) servletRequest);
            System.out.println("JwtAuthenticationFilter !!! ");
            if(token != null && jwtProviders.validateToken(token)) {
                Authentication authentication = jwtProviders.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(servletRequest,servletResponse);
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) &&
            bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
