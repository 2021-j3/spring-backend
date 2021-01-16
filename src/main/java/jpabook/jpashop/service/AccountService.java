package jpabook.jpashop.service;


import jpabook.jpashop.domain.Account;
import jpabook.jpashop.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    @Autowired
    private final AccountRepository accountRepository;

    public Long login(Account account){
        accountRepository.save(account);
        return account.getAccount_id();
    }
}
