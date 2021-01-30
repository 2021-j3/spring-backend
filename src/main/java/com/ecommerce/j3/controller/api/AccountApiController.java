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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Api(tags = {"1. Account"})
@Slf4j
@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountApiController implements CrudInterface<AccountApiRequest, AccountApiResponse> {
    private final AccountApiLogicService accountApiLogicService;
    private final AccountService accountService;
    private final AccountRepository accountRepository;

    @ApiOperation(value = "회원 추가", notes = "회원을 추가한다.")
    @PostMapping("")
    @Override
    public BodyData<AccountApiResponse> create(@RequestBody BodyData<AccountApiRequest> request) {
        log.info("{}",request);
        return accountApiLogicService.create(request);
    }

    @ApiOperation(value = "회원 조회", notes = "id에 해당하는 회원을 조회한다.")
    @GetMapping("{id}")
    @Override
    public BodyData<AccountApiResponse> read(@PathVariable Long id) {
        log.info("read id: {}", id);
        return accountApiLogicService.read(id);
    }

    @ApiOperation(value = "회원 수정", notes = "회원을 수정한다.")
    @PutMapping("{id}")
    @Override
    public BodyData<AccountApiResponse> update(@RequestBody BodyData<AccountApiRequest> request) {
        return accountApiLogicService.update(request);
    }
    
    @ApiOperation(value = "회원 삭제", notes = "회원을 삭제한다.")
    @DeleteMapping("{id}")
    @Override
    public BodyData delete(@PathVariable Long id) {
        log.info("delete : {}",id);
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
