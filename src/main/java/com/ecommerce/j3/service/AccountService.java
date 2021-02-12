package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.entity.*;
import com.ecommerce.j3.domain.mapper.AccountMapper;
import com.ecommerce.j3.controller.dto.AccountDto.AccountApiRequest;
import com.ecommerce.j3.controller.dto.AccountDto.AccountApiResponse;
import com.ecommerce.j3.repository.AccountRepository;
import lombok.RequiredArgsConstructor;

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
@RequiredArgsConstructor  // final 변수만 처리.
public class AccountService implements UserDetailsService {


    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    /* @Autowired : Spring이 auto 로 주입해줌. 단, 생성자가 한개일때!!
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }*/

    @Transactional
    public Long join(Account account)  {
        validateDuplicateAccount(account);
        accountRepository.save(account);
        return account.getAccountId();  // em 이 생성해줌
    }
    @Transactional
    public Order makeOrder(Account account, OrderItem orderItem){
     //   Order order = Order.createorder(account,orderItem);
        return new Order();

    }

    private void validateDuplicateAccount(Account account){
        //List<Account> findAccounts= accountRepository.findByName(account.getNickname());
        // if(!findAccounts.isEmpty()){
         //    throw new IllegalStateException("이미 존재하는 회원입니다.");}

    }

    @Transactional(readOnly = true)
    public List<Account> findAccounts() {
        return accountRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Account findOne(Long id){
        Account account = accountRepository.findById(id).orElseThrow(
                ()->new RuntimeException("cannot find")
        );
        return account;
    }
    public Account findByEmail(String email){
        return accountRepository.findByEmail(email).orElseThrow(()->new RuntimeException("cannot find"));
    }

    public AccountApiResponse store(AccountApiRequest accountInfo){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // Bean 에 등록된 인코더불러오기
        accountInfo.setPasswordHash(passwordEncoder.encode(accountInfo.getPasswordHash())); // 해시처리
        accountInfo.setAccountType(AccountType.USER); // 웹 페이지에서 처음 생성 시 무조건 user
        Account account = accountMapper.toEntity(accountInfo); // dto -> entity
        accountRepository.save(account);
        return accountMapper.toDto(account);
    }

    public AccountApiResponse findById(Long accountId){
        Account account = accountRepository
                .findById(accountId)
                .orElseThrow(()->new UsernameNotFoundException("다음 정보로 Account를 찾을 수 없습니다: accountId=" + accountId.toString()));
        return accountMapper.toDto(account);
    }

    public AccountApiResponse update(AccountApiRequest accountInfo){
        Account accountFromDB = accountRepository.findByEmail(accountInfo.getEmail()).orElseThrow(()->new RuntimeException("cannot find"));
//                .findByEmail(accountInfo.getEmail()).orElseThrow(()->new UsernameNotFoundException("DB에서 유저가 삭제됬습니다"));
        accountMapper.updateFromDto(accountFromDB, accountInfo);
        accountRepository.save(accountFromDB);
        return accountMapper.toDto(accountFromDB);
    }

    public void delete(AccountApiRequest accountInfo){
        // 에러처리
    }

        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(username).orElseThrow(()->new RuntimeException("cannot find"));
//                .findByEmail(username).orElseThrow(()->new UsernameNotFoundException(username));
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
