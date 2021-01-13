package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.Account;
import com.ecommerce.j3.domain.Order;
import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.repository.OrdersRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Transactional
@SpringBootTest
class OrderServiceTest {
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
        account.setNickname("ORDERS");
        account.setPasswordHash("passHash");
        account.setEmail("example1@mail.com");
        account.setPhoneNumber("000-000-000");
        accountService.save(account);
        accountId = account.getAccountId();
    }

    @Test
    void save(){
        // given
        Order order = new Order();
        order.setSessionId("2132");
        order.setToken("4124");
        order.setStatus((short) 1);
        order.setItemPriceTotal(BigDecimal.valueOf(124));
        order.setItemDiscount(1.23f);
        order.setTax(0.213f);
        order.setShipping(BigDecimal.valueOf(231.214f));
        order.setUserDiscount(123.13f);
        order.setGrandTotal(213.31f);
        order.setAccount(accountService.findOne(accountId).get());
        order.setZipCode(84984);
        // when
        ordersService.save(order);

        //then
        Order orderFromDB = ordersRepository.getOne(order.getOrderId());
        Assertions
                .assertThat(order.getGrandTotal())
                .isEqualTo(orderFromDB.getGrandTotal());
    }

    @Test
    void update() {
        // given
        Order order = new Order();
        order.setSessionId("2132");
        order.setToken("4124");
        order.setStatus((short) 1);
        order.setItemPriceTotal(BigDecimal.valueOf(124));
        order.setItemDiscount(1.23f);
        order.setTax(0.213f);
        order.setShipping(BigDecimal.valueOf(231.214f));
        order.setUserDiscount(123.13f);
        order.setGrandTotal(213.31f);
        order.setAccount(accountService.findOne(accountId).get());
        order.setZipCode(84984);
        ordersService.save(order);
        // when
        order.setGrandTotal(31234f);
        ordersService.update(order);

        //then
        Order orderFromDB = ordersRepository.getOne(order.getOrderId());
        Assertions
                .assertThat(order.getGrandTotal())
                .isEqualTo(orderFromDB.getGrandTotal());
    }

    @Test
    void findOneById() {
        // given
        Order order = new Order();
        order.setSessionId("2132");
        order.setToken("4124");
        order.setStatus((short) 1);
        order.setItemPriceTotal(BigDecimal.valueOf(124));
        order.setItemDiscount(1.23f);
        order.setTax(0.213f);
        order.setShipping(BigDecimal.valueOf(231.214f));
        order.setUserDiscount(123.13f);
        order.setGrandTotal(213.31f);
        order.setAccount(accountService.findOne(accountId).get());
        order.setZipCode(84984);
        ordersService.save(order);

        //then
        Order orderFromDB = ordersService.findOne(order.getOrderId()).get();
        Assertions
                .assertThat(order.getGrandTotal())
                .isEqualTo(orderFromDB.getGrandTotal());
    }

    @Test
    public void findByAccountId(){
        // given
        Order order = new Order();
        order.setSessionId("2132");
        order.setToken("4124");
        order.setStatus((short) 1);
        order.setItemPriceTotal(BigDecimal.valueOf(124));
        order.setItemDiscount(1.23f);
        order.setTax(0.213f);
        order.setShipping(BigDecimal.valueOf(231.214f));
        order.setUserDiscount(123.13f);
        order.setGrandTotal(213.31f);
        order.setAccount(accountService.findOne(accountId).get());
        order.setZipCode(84984);
        ordersService.save(order);


        // when

        //then
        int cnt_now = ordersService.findByAccount(accountService.findOne(accountId).get()).size();
        Assertions
                .assertThat(1)
                .isEqualTo(cnt_now);
    }


    @Test
    void remove() {
        // given
        Order order = new Order();
        order.setSessionId("2132");
        order.setToken("4124");
        order.setStatus((short) 1);
        order.setItemPriceTotal(BigDecimal.valueOf(124));
        order.setItemDiscount(1.23f);
        order.setTax(0.213f);
        order.setShipping(BigDecimal.valueOf(231.214f));
        order.setUserDiscount(123.13f);
        order.setGrandTotal(213.31f);
        order.setAccount(accountService.findOne(accountId).get());
        order.setZipCode(84984);
        ordersService.save(order);
        // when
        int cnt_that = ordersService.findAll().size();
        ordersService.remove(order);

        //then
        int cnt_now = ordersService.findAll().size();
        Assertions
                .assertThat(cnt_that - 1)
                .isEqualTo(cnt_now);
    }
}