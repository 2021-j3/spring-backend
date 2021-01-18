package com.ecommerce.j3.domain.repository;

import com.ecommerce.j3.J3ApplicationTests;

import com.ecommerce.j3.domain.entity.Payment;
import com.ecommerce.j3.domain.repository.AccountRepository;
import com.ecommerce.j3.domain.repository.OrderRepository;

import com.ecommerce.j3.domain.repository.PaymentRepository;
import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PaymentRepositoryTest extends J3ApplicationTests {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    @Test
    public void create(){
        Payment payment = new Payment();
        payment.setPaymentId(2L);
        payment.setAccount(accountRepository.getOne(2L));
        payment.setOrder(orderRepository.getOne(2L));
        payment.setCode("Code");
        payment.setType(0);
        payment.setStatus(0);
        payment.setCreatedAt(LocalDateTime.now());
        payment.setUpdatedAt(LocalDateTime.now());
        payment.setCode("Content");

        Payment newTransaction = paymentRepository.save(payment);
        Assert.assertNotNull(newTransaction);

    }
}