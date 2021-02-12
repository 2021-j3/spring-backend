package com.ecommerce.j3.controller.dto;


import com.ecommerce.j3.controller.api.ControllerCrudInterface;
import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.mapper.AccountMapper;
import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.service.AccountApiLogicService;
import com.ecommerce.j3.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

public class AddressDto {
    /**
     * 기본 crud request
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddressApiRequest {
        private Long addressId;
        private Long accountId;
        //        private Account account;
        private String address;
        private String roadAddress;
        private String city;
        private String province;
        private String country;
        private Integer zipCode;
    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddressApiResponse {
        private Long addressId;
        private Account account;
        private String address;
        private String roadAddress;
        private String city;
        private String country;
        private Integer zipCode;
    }

    @Api(tags = {"01. Account"})
    @Slf4j
    @RestController
    @RequestMapping("/api/accounts")
    @AllArgsConstructor
    public static class AccountApiController implements ControllerCrudInterface<AccountDto.AccountApiRequest, AccountDto.AccountApiResponse> {
        private final AccountApiLogicService accountService;
        private final CartService cartService;
        private final AccountRepository accountRepository;
        private final AccountMapper accountMapper;

        @ApiOperation(value = "회원 추가", notes="회원을 추가한다")
        @PostMapping("")
        public ResponseEntity<AccountDto.AccountApiResponse> getAccount(@RequestBody @Valid AccountDto.AccountApiRequest request) {
            accountService.save(request);
            return null;
        }

    //    @ApiOperation(value = "회원 추가", notes="회원을 추가한다")
    //    @PostMapping("")
    //    @Override
        public BodyData<AccountDto.AccountApiResponse> create(@RequestBody AccountDto.AccountApiRequest request) {
    //        accountService.save(request);
            return null;
        }

        @ApiOperation(value = "회원 읽기", notes = "회원을 가져온다")
        @Override
    //    @GetMapping
        public BodyData<AccountDto.AccountApiResponse> read(Long id) {
            try {
                return BodyData.OK(accountService.findOne(id));
            }catch (EntityNotFoundException e){
                return BodyData.ERROR("데이터가 없습니다");
            }
        }

        @ApiOperation(value = "회원 갱신", notes = "회원을 갱신한다.")
        @Override
        @PutMapping
        public BodyData<AccountDto.AccountApiResponse> update(@RequestBody AccountDto.AccountApiRequest request) {
            try{
                return BodyData.OK(accountService.update(request));
            }catch (EntityNotFoundException e){
                return BodyData.ERROR("데이터가 없습니다");
            }
        }

        @ApiOperation(value = "회원 삭제", notes = "회원을 삭제한다.")
        @Override
        @DeleteMapping
        public BodyData delete(Long id) {
            try{
                accountService.remove(id);
                return BodyData.OK();
            }catch (EntityNotFoundException e){
                return BodyData.ERROR("데이터가 없습니다");
            }
        }

        //////////////////// 서비스 로직 ////////////////////
        @GetMapping("/api/accounts")
        public BodyData<AccountDto.AccountApiResponse> showAccount(@RequestBody @Valid AccountDto.CreateAccountRequest request) {

         /* MultiValueMap<String,String> responseHeaders = new LinkedMultiValueMap<>();
           responseHeaders.add("AUTHCODE","20210122");  // Sample Test for setting a BodyData.
          responseHeaders.add("TOKEN", "0443");
        return new ResponseEntity<String>(String.valueOf(new ReadAccountResponse(account.getAccountId(),account.getFirstName(),account.getLastName())), responseHeaders, HttpStatus.OK);
    */
            return accountService.readByEmail(request.getEmail());

        }


        @PostMapping("/api/accounts")
        public AccountDto.CreateAccountResponse saveAccount(@RequestBody @Valid AccountDto.CreateAccountRequest request) {
    //        Account account = Account.builder()
    //                .email(request.getEmail())
    //                .passwordHash(request.getPassword())
    //                .lastName(request.getLastname())
    //                .firstName(request.getFirstname())
    //                .gender(request.getGender())
    //                .accountType(request.getAccounttype())
    //                .build();
    //        Long id = accountService.save(request);
    //        return new CreateAccountResponse(id, account.getRegisteredAt(), account.getFirstName(), account.getLastName());
            Account account = accountMapper.toEntity(accountMapper.toRequestDto(request));
            accountRepository.save(account);
            return accountMapper.toCreateAccountResponse(account);
        }

        @PutMapping("/api/accounts")
        public AccountDto.CreateAccountResponse updateAccount(@RequestBody @Valid AccountDto.UpdateAccountRequest request) {
    //        Account accountFromDB = accountRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("cannot find"));
    //        AccountApiRequest fullRequest = accountMapper.toDto(request);
    //        accountMapper.updateFromDto(accountFromDB, fullRequest);
    //        accountService.save(accountFromDB);   // 준영속 컨텍스트 핸들링
    //
    //        return new CreateAccountResponse(accountFromDB.getAccountId(), accountFromDB.getRegisteredAt(), accountFromDB.getFirstName(), accountFromDB.getLastName());
            return null;
        }
    }
}
