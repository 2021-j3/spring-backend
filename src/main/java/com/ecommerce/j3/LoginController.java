package com.ecommerce.j3;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class LoginController {
//    AuthenticationManager authenticationManager;
//    AccountService accountService;

//    @PostMapping("/login")
//    public AccountLoginResponse login(@RequestBody AccountApiRequest.AccountLoginRequest accountAuthRequest, HttpSession session){
//        String email = accountAuthRequest.getEmail();
//        String password = accountAuthRequest.getPassword();
////        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
////        password = encoder.encode(password);
//
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
//        Authentication authentication = authenticationManager.authenticate(token);
//
//
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
//        UserDetails user = accountService.loadUserByUsername(email);
//        return new AccountLoginResponse(user.getUsername(), user.getAuthorities(), session.getId());
//    }

//    @PostMapping("/register")
//    public String register(@RequestBody AccountDTO account){
//        accountService.store(account);
//        return "success";
//    }
}
