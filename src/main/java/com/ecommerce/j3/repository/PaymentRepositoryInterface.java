package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.Account;
import com.ecommerce.j3.domain.Payment;

import java.util.List;

public interface PaymentRepositoryInterface {
    List<Payment> findByAccount(Account account);
}