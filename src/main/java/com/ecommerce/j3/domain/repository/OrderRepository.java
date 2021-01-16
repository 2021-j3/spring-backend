package com.ecommerce.j3.domain.repository;

import com.ecommerce.j3.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
