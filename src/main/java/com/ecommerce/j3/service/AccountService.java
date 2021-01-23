package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.entity.AccountDTO;
import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.AccountMapper;
import com.ecommerce.j3.domain.entity.AccountType;
import com.ecommerce.j3.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@AllArgsConstructor
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

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
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // Bean 에 등록된 인코더불러오기
        accountInfo.setPassword(passwordEncoder.encode(accountInfo.getPassword())); // 해시처리
        accountInfo.setAccountType(AccountType.USER); // 웹 페이지에서 처음 생성 시 무조건 user
        Account account = accountMapper.toEntity(accountInfo); // dto -> entity
        accountRepository.save(account);
        return accountInfo;
    }

    public AccountDTO update(AccountDTO accountInfo){
        Account accountFromDB = accountRepository
                .findByEmail(accountInfo.getEmail())
                .orElseThrow(()->new UsernameNotFoundException("DB에서 유저가 삭제됬습니다"));
        accountMapper.updateFromDTO(accountInfo, accountFromDB);
        accountRepository.save(accountFromDB);
        return accountMapper.toDTO(accountFromDB);
    }

    public AccountDTO findById(Long accountId){
        Account account = accountRepository
                .findById(accountId)
                .orElseThrow(()->new UsernameNotFoundException("다음 정보로 Account를 찾을 수 없습니다: accountId=" + accountId.toString()));
        return accountMapper.toDTO(account);
    }

    public AccountDTO findByEmail(String email) {
        Account account = accountRepository
                .findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("다음 정보로 Account를 찾을 수 없습니다: email=" + email));
        return accountMapper.toDTO(account);
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
