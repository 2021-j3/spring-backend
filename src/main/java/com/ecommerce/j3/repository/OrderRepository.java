package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.entity.Cart;
import com.ecommerce.j3.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value="SELECT * FROM orders RIGHT JOIN account ON orders.account_id = account.account_id", nativeQuery = true)
    Optional<Order> findByAccountId(@Param("account_id") Long accountId);
}
