package com.ecommerce.j3.controller.api;

import com.ecommerce.j3.controller.dto.AccountDto;
import com.ecommerce.j3.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Collection;

@RestController
@RequestMapping("/api/login")
@AllArgsConstructor
public class AuthApiController {
    private final AuthenticationManager authenticationManager;
    private final AccountService accountService;

    @Data
    @AllArgsConstructor
    public static class AuthenticationToken {
        private String username;
        private Collection<? extends GrantedAuthority> authorities;
        private String token;
    }


    @PostMapping
    public AuthenticationToken  login(@RequestBody AccountDto.LoginRequest loginRequest, HttpSession session){
        final UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        final Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

        final UserDetails details = accountService.loadUserByUsername(loginRequest.getEmail());
        return new AuthenticationToken(details.getUsername(), details.getAuthorities(), session.getId());
    }
}
