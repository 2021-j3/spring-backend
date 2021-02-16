package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> getByOrderItemIdIn(List<Long> orderItemIds);
}
