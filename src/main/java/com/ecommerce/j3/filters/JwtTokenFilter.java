package com.ecommerce.j3.filters;

import com.ecommerce.j3.domain.J3UserDetails;
import com.ecommerce.j3.service.AccountApiLogicService;
import com.ecommerce.j3.util.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final AccountApiLogicService accountApiLogicService;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        // jwt 토큰 타입이라면 검사
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            String token = authorizationHeader.substring(7); // "Bearer " 7자리 빼고 검사
            String email = jwtTokenUtil.getEmailFromToken(token);
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                if (jwtTokenUtil.validateToken(token)) {
                    J3UserDetails userDetails = accountApiLogicService.loadUserByUsername(email);
                    UsernamePasswordAuthenticationToken authentication
                            = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
