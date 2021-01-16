package com.ecommerce.j3.domain.repository;

import com.ecommerce.j3.domain.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
