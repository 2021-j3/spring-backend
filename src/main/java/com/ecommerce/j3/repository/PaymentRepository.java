package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByAccount(Account account);
}
