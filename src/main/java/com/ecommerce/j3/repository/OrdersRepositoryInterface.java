package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.Account;
import com.ecommerce.j3.domain.Orders;

import java.util.List;

public interface OrdersRepositoryInterface {
    List<Orders> findByAccount(Account account);
}
