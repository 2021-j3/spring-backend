package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.AccountDTO;
import com.ecommerce.j3.domain.Account;
import com.ecommerce.j3.domain.AccountType;
import com.ecommerce.j3.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import java.util.*;

@Service
@Transactional
public class AccountService implements UserDetailsService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){this.accountRepository = accountRepository;}


    public Account save(Account account){
        accountRepository.save(account);
        return account;
    }

    public Account update(Account account){
        accountRepository.save(account);
        return account;
    }

    public Optional<Account> findOne(Long accountId){
        return accountRepository.findById(accountId);
    }


    public void remove(Account account){
        accountRepository.delete(account);
    }

    public AccountDTO save(AccountDTO accountInfo){
        Account account = accountInfo.toEntity(); // dto -> entity
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // Bean 에 등록된 인코더불러오기
        account.setPasswordHash(passwordEncoder.encode(accountInfo.getPassword())); // 해시처리
        account.setAccountType(AccountType.USER); // 웹 페이지에서 처음 생성 시 무조건 user
        accountRepository.save(account);
        return accountInfo;
    }

    public AccountDTO update(AccountDTO accountInfo){
        Account accountFromDB = accountRepository
                .findByEmail(accountInfo.getEmail())
                .orElseThrow(()->new UsernameNotFoundException("DB에서 유저가 삭제됬습니다"));
        // ... 이렇게 하나씩 바꾸다가 unique 옵션을 위배할 수도 있음
        accountRepository.save(accountFromDB);
        return new AccountDTO(accountFromDB);
    }

    public AccountDTO findById(Long accountId){
        Account account = accountRepository
                .findById(accountId)
                .orElseThrow(()->new UsernameNotFoundException("다음 정보로 Account를 찾을 수 없습니다: accountId=" + accountId.toString()));
        return new AccountDTO(account);
    }

    public AccountDTO findByEmail(String email) {
        Account account = accountRepository
                .findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("다음 정보로 Account를 찾을 수 없습니다: email=" + email));
        return new AccountDTO(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository
                .findByEmail(username).orElseThrow(()->new UsernameNotFoundException(username));
        return new User(account.getEmail(),
                account.getPasswordHash(),
                authorities(account.getAccountType()));
    }

    private Collection<GrantedAuthority> authorities(AccountType accountType){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(accountType.toString()));
        return authorities;
    }
}
