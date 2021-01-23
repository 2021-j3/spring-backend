package com.ecommerce.j3.controller;

import com.ecommerce.j3.domain.Account;
import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.service.AccountService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.http.HttpHeaders;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AccountApiController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;

    @GetMapping("/api/accounts")
    public ResponseEntity<String> showAccount(@RequestBody @Valid CreateAccountRequest request) {

        MultiValueMap<String,String> responseHeaders = new LinkedMultiValueMap<>();
        responseHeaders.add("AUTHCODE","20210122");  // Sample Test for setting a header.
        responseHeaders.add("TOKEN", "0443");

        Account account = accountService.findByEmail(request.getEmail());

        return new ResponseEntity<String>(String.valueOf(new ReadAccountResponse(account.getAccountId(),account.getFirstName(),account.getLastName())), responseHeaders, HttpStatus.OK);
    }


    @PostMapping("/api/accounts")
    public CreateAccountResponse saveAccount(@RequestBody @Valid CreateAccountRequest request) {
        Account account = new Account();
        account.setNickname(request.getNickname());
        account.setEmail(request.getEmail());
        account.setPasswordHash(request.getPassword());
        account.setRegisteredAt(LocalDateTime.now());
        account.setLastName(request.getLastname());
        account.setFirstName(request.getFirstname());

        Long id = accountService.join(account);
        return new CreateAccountResponse(id,account.getRegisteredAt(),account.getFirstName(),account.getLastName());
    }

    @PutMapping("/api/accounts")
    public CreateAccountResponse updateAccount(@RequestBody @Valid UpdateAccountRequest request) {
        Account account = accountRepository.findByEmail(request.getEmail());
        account.setRegisteredAt(LocalDateTime.now());
        account.setLastName(request.getLastname());
        account.setFirstName(request.getFirstname());
        accountService.join(account);   // 준영속 컨텍스트 핸들링

        return new CreateAccountResponse(account.getAccountId(),account.getRegisteredAt(),account.getFirstName(),account.getLastName());
    }

    @Data
    static class ReadAccountRequest {
        private String email;
    }
    @Data
    static class CreateAccountRequest {
        private String email;
        private String nickname;
        private String password;
        private  String lastname;
        private String firstname;
    }
    @Data
    static class UpdateAccountRequest {
        private String email;
        private String lastname;
        private String firstname;
    }
    @Data
    class ReadAccountResponse {

        private Long id;
        private String firstName;
        private String lastName;
        public ReadAccountResponse(Long id,String firstName,String lastName) { /* Constructor */
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;

        }
    }
    @Data
    class CreateAccountResponse {

        private Long id;
        private LocalDateTime dateTime;
        private String firstName;
        private String lastName;
        public CreateAccountResponse(Long id,LocalDateTime dateTime,String firstName,String lastName) {
            this.id = id;
            this.dateTime = dateTime;
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }
    @Data
    class UpdateAccountResponse {

        private Long id;
        private LocalDateTime dateTime;
        private String firstName;
        private String lastName;
        public UpdateAccountResponse(Long id,LocalDateTime dateTime,String firstName,String lastName) {
            this.id = id;
            this.dateTime = dateTime;
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

}
