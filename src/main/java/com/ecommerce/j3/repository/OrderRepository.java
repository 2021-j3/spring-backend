package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.Account;
import com.ecommerce.j3.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, OrdersRepositoryInterface {
    @Override
    List<Order> findByAccount(Account account);
}
