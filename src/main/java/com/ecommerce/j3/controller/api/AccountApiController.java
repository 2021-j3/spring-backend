package com.ecommerce.j3.controller.api;


import com.ecommerce.j3.controller.ErrorType;
import com.ecommerce.j3.controller.dto.AccountDto;
import com.ecommerce.j3.controller.dto.AccountDto.*;
import com.ecommerce.j3.controller.dto.BodyData;
import com.ecommerce.j3.domain.J3UserDetails;
import com.ecommerce.j3.domain.mapper.AccountMapper;
import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.service.AccountApiLogicService;
import com.ecommerce.j3.service.CartApiLogicService;
import com.ecommerce.j3.util.CookieUtil;
import com.ecommerce.j3.util.JwtTokenUtil;
import com.ecommerce.j3.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
    private final RedisUtil redisUtil;

    @ApiOperation(value = "회원 추가", notes = "회원을 추가한다")
    @PostMapping("")
    public ResponseEntity<AccountApiResponse> create(@RequestBody AccountApiRequest request) {
        AccountApiResponse response = accountApiLogicService.saveAccount(request);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "회원 읽기", notes = "회원을 가져온다")
    @GetMapping("{id}")
    public BodyData<AccountDto.AccountApiResponse> read(@PathVariable("id") Long id) {
        try {
            return BodyData.OK(accountApiLogicService.findAccount(id));
        } catch (EntityNotFoundException e) {
            return BodyData.ERROR("데이터가 없습니다");
        }
    }

    @ApiOperation(value = "회원 갱신", notes = "회원을 갱신한다.")
//    @PutMapping("{id}") // 로그인된 상태이므로 굳이 필요없음
    @PutMapping("/my")
    public ResponseEntity<AccountDto.AccountApiResponse> update(@RequestBody AccountDto.UpdateAccountRequest request, Authentication authentication) {
        J3UserDetails userDetails = (J3UserDetails)authentication.getPrincipal();
        String password = userDetails.getPassword();
        // 권한이 없음
        if (! password.equals(request.getPassword()))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        // 권한 있는 경우 저장된 id 부여
        request.setAccountId(userDetails.getAccountId());
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

    /** 2021-02-26 penguin
     * @param request
     * @return
     */
    @ApiOperation(value = "회원 등록", notes = "등록한다")
    @PostMapping("/register")
    public ResponseEntity<AccountApiResponse> register(@RequestBody AccountApiRequest request) {
        AccountApiResponse response = accountApiLogicService.saveAccount(request);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "회원 삭제", notes= "탈퇴한다")
    @PostMapping("/withdrawal")
    public ResponseEntity withdrawal(@RequestBody LoginRequest withdrawalRequest){
        String email = withdrawalRequest.getEmail();
        String password = withdrawalRequest.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
        try {
            authenticationManager.authenticate(token);
            J3UserDetails userDetails = (J3UserDetails)accountApiLogicService.loadUserByUsername(email);
            accountApiLogicService.removeAccount(userDetails.getAccountId());
            return ResponseEntity.ok(null);
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("회원정보가 일치하지 않습니다");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("에러");
        }
    }
    /** 2021-03-07 penguin418
     * 로그인
     * session 을 사용하므로,
     * @param loginRequest { dto } email 과 password 필드를 가진 request
     * @return { ResponseEntity }
     */
    @ApiOperation(value = "회원 로그인", notes = "로그인한다.")
    @PostMapping("/login")
    public ResponseEntity<AccountLoginResponse> login(@RequestBody LoginRequest loginRequest,
                                                      HttpServletResponse res) {
        final String email = loginRequest.getEmail();
        final String password = loginRequest.getPassword();
        final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
        try {
            authenticationManager.authenticate(token);
//            AccountLoginResponse loginResponse = accountApiLogicService.Login(email);
            J3UserDetails userDetails = accountApiLogicService.loadUserByUsername(email);
            final String accessToken = jwtTokenUtil.issueAccessToken(userDetails);
            final String refreshToken = jwtTokenUtil.issueRefreshToken(userDetails);
            CookieUtil.addCookie(res, JwtTokenUtil.ACCESS_TOKEN_NAME, accessToken, JwtTokenUtil.ACCESS_EXPIRATION_MS);
            CookieUtil.addCookie(res, JwtTokenUtil.REFRESH_TOKEN_NAME, refreshToken, JwtTokenUtil.REFRESH_EXPIRATION_MS);
            redisUtil.setDataExpire(refreshToken, userDetails.getUsername(), JwtTokenUtil.REFRESH_EXPIRATION_MS);
            AccountLoginResponse loginResponse = new AccountLoginResponse(
                    userDetails.getUsername(),
                    userDetails.getAuthorities(),
                    refreshToken);
            // user info 알려줌
            res.addHeader("firstName", userDetails.getFirstName());
            return ResponseEntity.ok(loginResponse);
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("회원정보가 일치하지 않습니다");
        }
    }

    @ApiOperation(value = "로그아웃 한다", notes = "로그아웃 한다.")
    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletRequest req, HttpServletResponse res){
        Cookie refreshToken = CookieUtil.getCookie(req, JwtTokenUtil.REFRESH_TOKEN_NAME);
        if (refreshToken != null){
            redisUtil.deleteData(refreshToken.getValue());
        }
        CookieUtil.deleteCookie(res, JwtTokenUtil.ACCESS_TOKEN_NAME);
        CookieUtil.deleteCookie(res, JwtTokenUtil.REFRESH_TOKEN_NAME);
        return ResponseEntity.noContent().build(); // 2021-03-07 penguin: refresh
    }

    /**
     * 로기인된 유저의 정보를 불러온다
     * @param authentication
     * @return
     */
    @PreAuthorize("hasRole('ROLE_USER')") // 2021-03-07 penguin418: 권한이 없는 사용자에게 403을 리턴합니다
    @GetMapping("/my")
    public ResponseEntity<AccountApiResponse> getMyAccount(Authentication authentication){
        J3UserDetails userDetails = (J3UserDetails)authentication.getPrincipal();

        AccountApiResponse account = accountApiLogicService.findAccount(userDetails.getAccountId());
        return ResponseEntity.ok(account);
    }

    // 회원가입 문제
    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ExceptionHandler(EntityExistsException.class)
    public ErrorType entityExistException(Exception e){
        String message = e.getMessage();
        if (message.split("\\s")[1].equals("이메일"))
            return ErrorType.EmailExists;
        else
            return ErrorType.PhoneNumberExists;
    }

    // 로그인 문제
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorType entityNotFoundException(Exception e){
        return ErrorType.AccountNotFound;
    }
}