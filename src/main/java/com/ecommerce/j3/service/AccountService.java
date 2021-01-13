package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.Account;
import com.ecommerce.j3.domain.Review;
import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
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
}
