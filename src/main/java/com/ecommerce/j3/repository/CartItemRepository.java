package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> getByCartItemIdIn(List<Long> cartItemIds);
}
