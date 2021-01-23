package com.ecommerce.j3;

import com.ecommerce.j3.domain.network.request.AccountAuthRequest;
import com.ecommerce.j3.domain.network.response.AccountAuthResponse;
import com.ecommerce.j3.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/auth/login")
public class LoginController {
    AuthenticationManager authenticationManager;
    AccountService accountService;

    @PostMapping("")
    public AccountAuthResponse login(@RequestBody AccountAuthRequest accountAuthRequest, HttpSession session){
        String email = accountAuthRequest.getEmail();
        String password = accountAuthRequest.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        password = encoder.encode(password);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManager.authenticate(token);



        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        UserDetails user = accountService.loadUserByUsername(email);
        return new AccountAuthResponse(user.getUsername(), user.getAuthorities(), session.getId());
    }
}
