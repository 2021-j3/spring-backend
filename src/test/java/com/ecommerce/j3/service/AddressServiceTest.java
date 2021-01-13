package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.Account;
import com.ecommerce.j3.domain.Address;
import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.repository.AddressRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class AddressServiceTest {
    @Autowired AccountService accountService;
    @Autowired AccountRepository accountRepository;
    @Autowired
    AddressService addressService;
    @Autowired
    AddressRepository addressRepository;

     Long accountId;

    @BeforeEach
    void beforeEach(){
        // given
        Account account = new Account();
        account.setNickname("nick");
        account.setPasswordHash("passHash");
        account.setEmail("example1@mail.com");
        account.setPhoneNumber("000-000-000");
        accountService.save(account);
        accountId = account.getId();
    }

    @Test
    void save(){
        // given
        Address address = new Address();
        address.setRoad_address("address");
        address.setAccount(accountService.findOne(accountId).get());
        // when
        addressService.save(address);

        //then
        Address addressFromDB = addressRepository.getOne(address.getId());
        Assertions
                .assertThat(address.getRoad_address())
                .isEqualTo(addressFromDB.getRoad_address());
    }

    @Test
    void update() {
        // given
        Address address = new Address();
        address.setRoad_address("address");
        address.setAccount(accountService.findOne(accountId).get());
        addressService.save(address);
        // when
        address.setRoad_address("new_address");
        addressService.update(address);

        //then
        Address addressFromDB = addressRepository.getOne(address.getId());
        Assertions
                .assertThat(address.getRoad_address())
                .isEqualTo(addressFromDB.getRoad_address());
    }

    @Test
    void findOneById() {
        // given
        Address address = new Address();
        address.setRoad_address("address");
        address.setAccount(accountService.findOne(accountId).get());
        addressService.save(address);

        //then
        Address addressFromDB = addressRepository.getOne(address.getId());
        Assertions
                .assertThat(address.getRoad_address())
                .isEqualTo(addressFromDB.getRoad_address());
    }

    @Test
    void remove() {
        // given
        Address address = new Address();
        address.setRoad_address("address");
        address.setAccount(accountService.findOne(accountId).get());
        addressService.save(address);
        // when
        int cnt_that = addressService.findAll().size();
        addressService.remove(address);

        //then
        int cnt_now = addressService.findAll().size();
        Assertions
                .assertThat(cnt_that - 1)
                .isEqualTo(cnt_now);
    }
}