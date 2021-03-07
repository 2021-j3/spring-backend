package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.Order;

import java.util.List;

public interface OrdersRepositoryInterface {
    List<Order> findByAccount(Account account);
}
