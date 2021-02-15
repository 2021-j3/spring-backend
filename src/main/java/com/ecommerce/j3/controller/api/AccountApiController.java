package com.ecommerce.j3.controller.api;


import com.ecommerce.j3.controller.dto.AccountDto;
import com.ecommerce.j3.controller.dto.AccountDto.*;
import com.ecommerce.j3.controller.dto.BodyData;
import com.ecommerce.j3.domain.J3UserDetails;
import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.mapper.AccountMapper;
import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.service.AccountApiLogicService;
import com.ecommerce.j3.service.CartApiLogicService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Api(tags = {"01. Account"})
@Slf4j
@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountApiController {
    private final AccountApiLogicService accountApiLogicService;
    private final CartApiLogicService cartService;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final AuthenticationManager authenticationManager;

    @ApiOperation(value = "회원 추가", notes = "회원을 추가한다")
    @PostMapping("")
    public ResponseEntity<AccountApiResponse> create(@RequestBody AccountApiRequest request) {
        AccountApiResponse response = accountApiLogicService.saveAccount(request);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "회원 일기", notes = "회원을 가져온다")
    @GetMapping("{id}")
    public BodyData<AccountDto.AccountApiResponse> read(@PathVariable("id") Long id) {
        try {
            return BodyData.OK(accountApiLogicService.findAccount(id));
        } catch (EntityNotFoundException e) {
            return BodyData.ERROR("데이터가 없습니다");
        }
    }

    @ApiOperation(value = "회원 갱신", notes = "회원을 갱신한다.")
    @PutMapping("{id}")
    public ResponseEntity<AccountDto.AccountApiResponse> update(@RequestBody AccountDto.AccountApiRequest request) {
        try {
            return new ResponseEntity<AccountDto.AccountApiResponse>(accountApiLogicService.updateAccount(request), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    @ApiOperation(value = "회원 삭제", notes = "회원을 삭제한다.")
    @DeleteMapping("{id}")
    public BodyData delete(Long id) {
        try {
            accountApiLogicService.removeAccount(id);
            return BodyData.OK();
        } catch (EntityNotFoundException e) {
            return BodyData.ERROR("데이터가 없습니다");
        }
    }

    //////////////////// 서비스 로직 ////////////////////

    /**
     * 로그인
     * session 을 사용하므로,
     * @param loginRequest { dto } email 과 password_hash 필드를 가진 request
     * @param session { session } jsessionid 쿠키를 삽입하기 위한 세션
     * @return { ResponseEntity }
     */
    @PostMapping("/login")
    public ResponseEntity<AccountLoginResponse> login(@RequestBody LoginRequest loginRequest, HttpSession session) throws JsonProcessingException {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        J3UserDetails user = accountApiLogicService.loadUserByUsername(email);
        return ResponseEntity.ok(new AccountLoginResponse(user.getUsername(), user.getAuthorities(), session.getId()));
    }

    /**
     * 로그인한 유저로부터 api 호출이 되었을때, authentication 에서 id 가 출력되는 지 확인
     * 방법: /api/accounts/login 의 response에서 token 을 복사하여 /api/accounts/test 헤더의 x-auth-token 으로 추가
     * @param authentication
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping("/test")
    public ResponseEntity<AccountLoginResponse> testInformation(Authentication authentication) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("auth:" + mapper.writeValueAsString(authentication));
        System.out.println("detail: " + mapper.writeValueAsString(authentication.getDetails()));
        J3UserDetails userDetails = (J3UserDetails)authentication.getDetails();
        return ResponseEntity.ok(new AccountLoginResponse(userDetails.getUsername(), userDetails.getAuthorities(), userDetails.getId().toString()));
    }

    //    @GetMapping("/api/accounts")
    public BodyData<AccountApiResponse> showAccount(@RequestBody @Valid CreateAccountRequest request) {

     /* MultiValueMap<String,String> responseHeaders = new LinkedMultiValueMap<>();
       responseHeaders.add("AUTHCODE","20210122");  // Sample Test for setting a BodyData.
      responseHeaders.add("TOKEN", "0443");
    return new ResponseEntity<String>(String.valueOf(new ReadAccountResponse(account.getAccountId(),account.getFirstName(),account.getLastName())), responseHeaders, HttpStatus.OK);
*/
        return BodyData.OK(accountApiLogicService.findAccountByEmail(request.getEmail()));
    }


    //    @PostMapping("/api/accounts")
    public CreateAccountResponse saveAccount(@RequestBody @Valid CreateAccountRequest request) {
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

    //    @PutMapping("/api/accounts")
    public CreateAccountResponse updateAccount(@RequestBody @Valid UpdateAccountRequest request) {
//        Account accountFromDB = accountRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("cannot find"));
//        AccountApiRequest fullRequest = accountMapper.toDto(request);
//        accountMapper.updateFromDto(accountFromDB, fullRequest);
//        accountService.save(accountFromDB);   // 준영속 컨텍스트 핸들링
//
//        return new CreateAccountResponse(accountFromDB.getAccountId(), accountFromDB.getRegisteredAt(), accountFromDB.getFirstName(), accountFromDB.getLastName());
        return null;
    }
}