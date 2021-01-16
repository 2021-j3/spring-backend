package com.ecommerce.j3.domain.repository;

import com.ecommerce.j3.J3ApplicationTests;
import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.AccountType;
import com.ecommerce.j3.domain.entity.GenderType;
import com.ecommerce.j3.domain.repository.AccountRepository;
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
    public void create(){
        // domain/Account Class 사용
        Account account = new Account();

        account.setNickname("Nickname");
        account.setPasswordHash("PasswordHash2");
        account.setFirstName("FirstName2");
        account.setLastName("LastName2");
        account.setGender(GenderType.MALE);
        account.setEmail("Email2");
        account.setBirthday(LocalDate.of(1996,03,25));
        account.setPhoneNumber("Number2");
        account.setRegisteredAt(LocalDateTime.now());
        account.setLastLogin(LocalDateTime.now());
        account.setAccountType(AccountType.USER);

        Account newAccount = accountRepository.save(account);

        Assert.assertNotNull(newAccount);

    }

}
