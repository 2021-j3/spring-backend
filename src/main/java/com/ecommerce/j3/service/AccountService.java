package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.entity.*;
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
        Order order = Order.createorder(account,orderItem);


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
        return accountRepository.findOne(id);
    }
    public Account findByEmail(String email){
        return accountRepository.findByEmail(email);
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
                .findByEmail(accountInfo.getEmail());
//                .findByEmail(accountInfo.getEmail()).orElseThrow(()->new UsernameNotFoundException("DB에서 유저가 삭제됬습니다"));
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository
                .findByEmail(username);
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
