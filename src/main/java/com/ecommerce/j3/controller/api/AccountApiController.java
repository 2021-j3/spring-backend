package com.ecommerce.j3.controller.api;

import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.AccountType;
import com.ecommerce.j3.domain.entity.GenderType;
import com.ecommerce.j3.domain.network.BodyData;
import com.ecommerce.j3.domain.network.request.AccountApiRequest;
import com.ecommerce.j3.domain.network.response.AccountApiResponse;
import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.service.AccountApiLogicService;
import com.ecommerce.j3.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountApiController implements CrudInterface<AccountApiRequest, AccountApiResponse> {
    private final AccountService accountService;
    private final AccountApiLogicService accountApiLogicService;
    private final AccountRepository accountRepository;

    @Override
    @PostMapping("")
    public BodyData<AccountApiResponse> create(@RequestBody BodyData<AccountApiRequest> request) throws JsonProcessingException {
        log.info("{}", request);

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(request));

        return accountApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public BodyData<AccountApiResponse> read(@PathVariable Long id) {
        log.info("read id: {}", id);
        return accountApiLogicService.read(id);
    }

    @Override
    @PutMapping("{id}")
    public BodyData<AccountApiResponse> update(@RequestBody BodyData<AccountApiRequest> request) {
        return accountApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public BodyData delete(@PathVariable Long id) {
        log.info("delete : {}", id);
        return accountApiLogicService.delete(id);
    }

    //////////////////// 서비스 로직 ////////////////////
    @GetMapping("/api/accounts")
    public BodyData<AccountApiResponse> showAccount(@RequestBody @Valid CreateAccountRequest request) {

     /* MultiValueMap<String,String> responseHeaders = new LinkedMultiValueMap<>();
       responseHeaders.add("AUTHCODE","20210122");  // Sample Test for setting a BodyData.
      responseHeaders.add("TOKEN", "0443");
    return new ResponseEntity<String>(String.valueOf(new ReadAccountResponse(account.getAccountId(),account.getFirstName(),account.getLastName())), responseHeaders, HttpStatus.OK);
*/
        return accountApiLogicService.readByEmail(request.getEmail());

    }


    @PostMapping("/api/accounts")
    public CreateAccountResponse saveAccount(@RequestBody @Valid CreateAccountRequest request) {
        Account account = Account.builder()
                .email(request.getEmail())
                .passwordHash(request.getPassword())
                .lastName(request.getLastname())
                .firstName(request.getFirstname())
                .gender(request.getGender())
                .accountType(request.getAccounttype())
                .build();
        Long id = accountService.join(account);
        return new CreateAccountResponse(id, account.getRegisteredAt(), account.getFirstName(), account.getLastName());
    }

    @PutMapping("/api/accounts")
    public CreateAccountResponse updateAccount(@RequestBody @Valid UpdateAccountRequest request) {
        Account account = accountRepository.findByEmail(request.getEmail());
        Account accountUpdate = Account.builder()
                .accountId(account.getAccountId())
                .email(account.getEmail())
                .passwordHash(account.getPasswordHash())
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .birthday(account.getBirthday())
                .gender(account.getGender())
                .phoneNumber(account.getPhoneNumber())
                .accountType(account.getAccountType())
                .registeredAt(LocalDateTime.now())
                .lastLogin(account.getLastLogin())
                .build();
        accountService.join(account);   // 준영속 컨텍스트 핸들링

        return new CreateAccountResponse(account.getAccountId(), account.getRegisteredAt(), account.getFirstName(), account.getLastName());
    }

    @Data
    static class ReadAccountRequest {
        private String email;
    }

    @Data
    static class CreateAccountRequest {
        private String email;
        private String password;
        private String lastname;
        private String firstname;
        private GenderType gender;
        private AccountType accounttype;
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

        public ReadAccountResponse(Long id, String firstName, String lastName) { /* Constructor */
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

        public CreateAccountResponse(Long id, LocalDateTime dateTime, String firstName, String lastName) {
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

        public UpdateAccountResponse(Long id, LocalDateTime dateTime, String firstName, String lastName) {
            this.id = id;
            this.dateTime = dateTime;
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }
}
