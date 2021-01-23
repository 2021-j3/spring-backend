package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.Account;
import com.ecommerce.j3.repository.AccountRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor  // final 변수만 처리.
public class AccountService {


    private final AccountRepository accountRepository;

    /* @Autowired : Spring이 auto 로 주입해줌. 단, 생성자가 한개일때!!
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }*/

    @Transactional
    public Long join(Account account){
        validateDuplicateAccount(account);
        accountRepository.save(account);
        return account.getAccountId();  // em 이 생성해줌
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


}
