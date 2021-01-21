package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.Account;
import com.ecommerce.j3.domain.Order;

import java.util.List;

public interface OrdersRepositoryInterface {
    List<Order> findByAccount(Account account);
}
