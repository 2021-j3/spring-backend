package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.Payment;

import java.util.List;

public interface PaymentRepositoryInterface {
    List<Payment> findByAccount(Account account);
}