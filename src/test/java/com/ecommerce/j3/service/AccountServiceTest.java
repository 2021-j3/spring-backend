package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.Account;
import com.ecommerce.j3.repository.AccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
class AccountServiceTest {
    @Autowired AccountService accountService;
    @Autowired AccountRepository accountRepository;

    @BeforeEach
    void beforeEach(){

    }

    @Test
    void save() {
        // given
        Account account = new Account();
        account.setNickname("ACCOUNT TEST");
        account.setPasswordHash("passHash");
        account.setEmail("example1@mail.com");
        account.setPhoneNumber("000-000-000");
        // when
        accountService.save(account);

        //then
        Account accountFromDB = accountRepository.getOne(account.getAccountId());
        Assertions
                .assertThat(account.getEmail())
                .isEqualTo(accountFromDB.getEmail());
    }

    @Test
    void update() {
        // given
        Account account = new Account();
        account.setNickname("nick");
        account.setPasswordHash("passHash");
        account.setEmail("example1@mail.com");
        account.setPhoneNumber("000-000-000");
        accountService.save(account);

        // when
        String new_mail = "new@mail.com";
        account.setEmail(new_mail);
        accountService.update(account);

        // then
        Account accountFromDB = accountRepository.getOne(account.getAccountId());
        Assertions
                .assertThat(account.getEmail())
                .isEqualTo(accountFromDB.getEmail());
    }

    @Test
    void findOneById() {
        // given
        Account account = new Account();
        account.setNickname("nick");
        account.setPasswordHash("passHash");
        account.setEmail("example1@mail.com");
        account.setPhoneNumber("000-000-000");
        accountService.save(account);

        Account accountFromDB = accountService.findOne(account.getAccountId()).get();
        Assertions
                .assertThat(account.getEmail())
                .isEqualTo(accountFromDB.getEmail());
    }
}