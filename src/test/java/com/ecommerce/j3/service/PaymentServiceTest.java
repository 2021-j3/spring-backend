package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.Account;
import com.ecommerce.j3.domain.Order;
import com.ecommerce.j3.domain.Payment;
import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.repository.OrderRepository;
import com.ecommerce.j3.repository.PaymentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Transactional
@SpringBootTest
class PaymentServiceTest {
    @Autowired AccountService accountService;
    @Autowired AccountRepository accountRepository;
    @Autowired PaymentService paymentService;
    @Autowired PaymentRepository paymentRepository;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    Long accountId;
    Long orderId;

    @BeforeEach
    void beforeEach(){
        // given
        Account account = new Account();
        account.setNickname("CART ITEM TEST");
        account.setPasswordHash("passHash");
        account.setEmail("example1@mail.com");
        account.setPhoneNumber("000-000-000");
        accountService.save(account);
        accountId = account.getAccountId();

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
        orderService.save(order);
        orderId = order.getOrderId();
    }

    @Test
    void save(){
        // given
        Payment payment = new Payment();
        payment.setAccount(accountService.findOne(accountId).get());
        payment.setOrder(orderService.findOne(orderId).get());
        payment.setCode("ready");
        payment.setType(1);
        payment.setStatus(1);
        // when
        paymentService.save(payment);

        //then
        Payment paymentFromDB = paymentRepository.getOne(payment.getPaymentId());
        Assertions
                .assertThat(payment.getAccount())
                .isEqualTo(paymentFromDB.getAccount());
    }

    @Test
    void update() {
        // given
        Payment payment = new Payment();
        payment.setAccount(accountService.findOne(accountId).get());
        payment.setOrder(orderService.findOne(orderId).get());
        payment.setCode("ready");
        payment.setType(1);
        payment.setStatus(1);
        paymentService.save(payment);
        // when
        payment.setStatus(2);
        paymentService.save(payment);

        //then
        Payment paymentFromDB = paymentRepository.getOne(payment.getPaymentId());
        Assertions
                .assertThat(payment.getStatus())
                .isEqualTo(paymentFromDB.getStatus());
    }

    @Test
    void findOneById() {
        // given
        Payment payment = new Payment();
        payment.setAccount(accountService.findOne(accountId).get());
        payment.setOrder(orderService.findOne(orderId).get());
        payment.setCode("ready");
        payment.setType(1);
        payment.setStatus(1);
        paymentService.save(payment);

        //then
        Payment paymentFromDB = paymentRepository.getOne(payment.getPaymentId());
        Assertions
                .assertThat(payment.getAccount())
                .isEqualTo(paymentFromDB.getAccount());
    }

    @Test
    void remove() {
        // given
        Payment payment = new Payment();
        payment.setAccount(accountService.findOne(accountId).get());
        payment.setOrder(orderService.findOne(orderId).get());
        payment.setCode("ready");
        payment.setType(1);
        payment.setStatus(1);
        paymentService.save(payment);
        // when
        List<Payment> paymentList = paymentService.findAll();
        int cnt_that = paymentList.size();
        paymentService.remove(payment);

        //then
        int cnt_now = paymentService.findAll().size();
        Assertions
                .assertThat(cnt_that - 1)
                .isEqualTo(cnt_now);
    }
}