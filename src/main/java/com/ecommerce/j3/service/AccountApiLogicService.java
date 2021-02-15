package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.AccountDto.AccountApiRequest;
import com.ecommerce.j3.controller.dto.AccountDto.AccountApiResponse;
import com.ecommerce.j3.controller.dto.AccountDto.AccountLoginResponse;
import com.ecommerce.j3.domain.J3UserDetails;
import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.AccountType;
import com.ecommerce.j3.domain.mapper.AccountMapper;
import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor  // final 변수만 처리.
public class AccountApiLogicService implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final JwtTokenUtil jwtTokenUtil;

    public AccountApiResponse saveAccount(AccountApiRequest accountApiRequest) {
        // 검사
        validateDuplicateAccountEmail(accountApiRequest);

        // 비밀번호 해시적용 및 기본적으로 유저타입 지정
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        accountApiRequest.setPasswordHash(encoder.encode(accountApiRequest.getPasswordHash()));
        accountApiRequest.setAccountType(AccountType.USER);
        Account account = accountMapper.toEntity(accountApiRequest);
        save(account);
        return accountMapper.toApiResponse(account);
    }

    public AccountApiResponse findAccount(Long accountId) {
        Account account = findById(accountId);
        return accountMapper.toApiResponse(account);
    }
    public AccountApiResponse findAccountByEmail(String email) {
        Account account = findByEmail(email);
        return accountMapper.toApiResponse(account);
    }

    public AccountApiResponse updateAccount(AccountApiRequest accountInfo) {
        Account accountFromDB = findById(accountInfo.getAccountId());
        accountMapper.updateFromDto(accountFromDB, accountInfo);
        accountRepository.save(accountFromDB);
        return accountMapper.toApiResponse(accountFromDB);
    }

    public void removeAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }

    public AccountLoginResponse Login(String email){
        J3UserDetails user = loadUserByUsername(email);
        return AccountLoginResponse.builder()
                .username(user.getUsername())
                .authorities(user.getAuthorities())
                .token(jwtTokenUtil.issue(user))
                .build();
    }


    /** 2021-02-15 penguin418
     * 실제 인증을 담당하는 AuthenticationManager 를 생성해주는 AuthenticationManagerBuilder 클래스에서 사용하는 UserDetail 인터페이스
     * @param email { username } 사용자 식별에 사용되는 유저이름, 여기서는 이메일을 사용
     * @return { UserDetails } 이메일, accountId, 권한이 담겨있음
     * @throws UsernameNotFoundException
     */
    @Override
    public J3UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("load user by username is called");
        Account account = findByEmail(email);
        if (account == null) throw new UsernameNotFoundException("일치하는 아이디를 찾을 수 없습니다");
        return new J3UserDetails(account);
    }

    // 프라이빗 한정자, AccountService 내에서 접근가능
    private void validateDuplicateAccountEmail(AccountApiRequest accountApiRequest) {
        if (findByEmail(accountApiRequest.getEmail()) != null)
            throw new EntityExistsException("존재하는 이메일");
    }



    // 패키지 한정자, service패키지 내에서만 접근 가능
    Account save(Account account){
        accountRepository.save(account);
        return account;
    }

    Account findById(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(EntityNotFoundException::new);
    }

    Account findByEmail(String email){
        return accountRepository.findByEmail(email).orElse(null);
    }


}
