package com.ecommerce.j3.domain.repository;

import com.ecommerce.j3.J3ApplicationTests;
import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.AccountType;
import com.ecommerce.j3.domain.entity.GenderType;
import com.ecommerce.j3.repository.AccountRepository;
import org.hibernate.annotations.ColumnTransformer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountRepositoryTest extends J3ApplicationTests {

    @Autowired
    private AccountRepository accountRepository;

    //    @Transactional
    @Test
    public void create() {
        // domain/Account Class 사용
        Account account = Account.builder()
                .passwordHash("PasswordHash2")
                .firstName("FirstName2")
                .lastName("LastName2")
                .gender(GenderType.MALE)
                .email("Email2")
                .birthday(LocalDate.of(1996, 3, 25))
                .phoneNumber("Number2")
                .registeredAt(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .accountType(AccountType.USER)
                .build();

        Account newAccount = accountRepository.save(account);

        Assert.assertNotNull(newAccount);

    }

}
