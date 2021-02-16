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
import com.ecommerce.j3.util.JwtTokenUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
    private final JwtTokenUtil jwtTokenUtil;

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
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @ApiOperation(value = "회원 삭제", notes = "회원을 삭제한다.")
    @DeleteMapping("{id}")
    public BodyData delete(@PathVariable("id") Long id) {
        try {
            accountApiLogicService.removeAccount(id);
            return BodyData.OK();
        } catch (EntityNotFoundException e) {
            return BodyData.ERROR("데이터가 없습니다");
        }
    }

    //////////////////// 서비스 로직 ////////////////////

    /** 2021-02-15 penguin418
     * 로그인
     * session 을 사용하므로,
     * @param loginRequest { dto } email 과 password 필드를 가진 request
     * @return { ResponseEntity }
     */
    @ApiOperation(value = "회원 로그인", notes = "회원 로그인한다.")
    @PostMapping("/login")
    public ResponseEntity<AccountLoginResponse> login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
        try {
            authenticationManager.authenticate(token);
            AccountLoginResponse loginResponse = accountApiLogicService.Login(email);

            // 2021-02-15 penguin418 쿠키 삽입,
            // TODO:  클라이언트에서 요청하는 헤더에 아래와 같은 토큰 추가해야 함
            String tt = "Bearer " + loginResponse.getToken();

            return ResponseEntity.ok(loginResponse);
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("회원정보가 일치하지 않습니다");
        }
    }

    /** 2021-02-15 penguin418
     * 로그인한 유저로부터 api 호출이 되었을때, authentication 에서 id 가 출력되는 지 확인하는 api
     * 방법: /api/accounts/login 의 response에서 token 을 복사하여 /api/accounts/test 헤더의 x-auth-token 으로 추가
     * @param authentication { Authentication } getPrincipal() 를 통해서 userDetail 객체를 얻을 수 있음
     * @return { ResponseEntity }
     * @throws JsonProcessingException { Exception } objectMapper에서 발생
     */
    @GetMapping("/test")
    public ResponseEntity<AccountLoginResponse> testInformation(Authentication authentication) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        J3UserDetails userDetails = (J3UserDetails)authentication.getPrincipal();
        System.out.println(userDetails.getUsername()); // email 이 출력됩니다
        System.out.println(userDetails.getAccountId()); // accountId가 출력됩니다
        System.out.println(userDetails.getAuthorities()); // 'ROLE_' + ACCOUNT_TYPE 이 출력됩니다
        return ResponseEntity.ok(new AccountLoginResponse(userDetails.getUsername(), userDetails.getAuthorities(), userDetails.getAccountId().toString()));
    }
}