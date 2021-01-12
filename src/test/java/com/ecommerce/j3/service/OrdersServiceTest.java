package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.Account;
import com.ecommerce.j3.domain.Orders;
import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.repository.OrdersRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Transactional
@SpringBootTest
class OrdersServiceTest {
    @Autowired AccountService accountService;
    @Autowired AccountRepository accountRepository;
    @Autowired
    OrdersService ordersService;
    @Autowired
    OrdersRepository ordersRepository;

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
        Orders orders = new Orders();
        orders.setSessionId("2132");
        orders.setToken("4124");
        orders.setStatus((short) 1);
        orders.setItemPriceTotal(BigDecimal.valueOf(124));
        orders.setItemDiscount(1.23f);
        orders.setTax(0.213f);
        orders.setShipping(231.214f);
        orders.setUserDiscount(123.13f);
        orders.setGrandTotal(213.31f);
        orders.setAccount(accountService.findOneById(accountId).get());
        // when
        ordersService.save(orders);

        //then
        Orders ordersFromDB = ordersRepository.getOne(orders.getId());
        Assertions
                .assertThat(orders.getGrandTotal())
                .isEqualTo(ordersFromDB.getGrandTotal());
    }

    @Test
    void update() {
        // given
        Orders orders = new Orders();
        orders.setSessionId("2132");
        orders.setToken("4124");
        orders.setStatus((short) 1);
        orders.setItemPriceTotal(BigDecimal.valueOf(124));
        orders.setItemDiscount(1.23f);
        orders.setTax(0.213f);
        orders.setShipping(231.214f);
        orders.setUserDiscount(123.13f);
        orders.setGrandTotal(213.31f);
        orders.setAccount(accountService.findOneById(accountId).get());
        ordersService.save(orders);
        // when
        orders.setGrandTotal(31234f);
        ordersService.update(orders);

        //then
        Orders ordersFromDB = ordersRepository.getOne(orders.getId());
        Assertions
                .assertThat(orders.getGrandTotal())
                .isEqualTo(ordersFromDB.getGrandTotal());
    }

    @Test
    void findOneById() {
        // given
        Orders orders = new Orders();
        orders.setSessionId("2132");
        orders.setToken("4124");
        orders.setStatus((short) 1);
        orders.setItemPriceTotal(BigDecimal.valueOf(124));
        orders.setItemDiscount(1.23f);
        orders.setTax(0.213f);
        orders.setShipping(231.214f);
        orders.setUserDiscount(123.13f);
        orders.setGrandTotal(213.31f);
        orders.setAccount(accountService.findOneById(accountId).get());
        ordersService.save(orders);

        //then
        Orders ordersFromDB = ordersService.findOneById(orders.getId()).get();
        Assertions
                .assertThat(orders.getGrandTotal())
                .isEqualTo(ordersFromDB.getGrandTotal());
    }

    @Test
    public void findByAccountId(){
        // given
        Orders orders = new Orders();
        orders.setSessionId("2132");
        orders.setToken("4124");
        orders.setStatus((short) 1);
        orders.setItemPriceTotal(BigDecimal.valueOf(124));
        orders.setItemDiscount(1.23f);
        orders.setTax(0.213f);
        orders.setShipping(231.214f);
        orders.setUserDiscount(123.13f);
        orders.setGrandTotal(213.31f);
        orders.setAccount(accountService.findOneById(accountId).get());
        ordersService.save(orders);


        // when

        //then
        int cnt_now = ordersService.findByAccount(accountService.findOneById(accountId).get()).size();
        Assertions
                .assertThat(1)
                .isEqualTo(cnt_now);
    }


    @Test
    void remove() {
        // given
        Orders orders = new Orders();
        orders.setSessionId("2132");
        orders.setToken("4124");
        orders.setStatus((short) 1);
        orders.setItemPriceTotal(BigDecimal.valueOf(124));
        orders.setItemDiscount(1.23f);
        orders.setTax(0.213f);
        orders.setShipping(231.214f);
        orders.setUserDiscount(123.13f);
        orders.setGrandTotal(213.31f);
        orders.setAccount(accountService.findOneById(accountId).get());
        ordersService.save(orders);
        // when
        int cnt_that = ordersService.findAll().size();
        ordersService.remove(orders);

        //then
        int cnt_now = ordersService.findAll().size();
        Assertions
                .assertThat(cnt_that - 1)
                .isEqualTo(cnt_now);
    }
}