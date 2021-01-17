package com.ecommerce.j3.domain.repository;

import com.ecommerce.j3.J3ApplicationTests;
import com.ecommerce.j3.domain.entity.OrderItem;
import com.ecommerce.j3.domain.repository.OrderRepository;
import com.ecommerce.j3.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

public class OrderItemRepositoryTest extends J3ApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository ordersRepository;

    @Transactional
    @Test
    void create(){
        OrderItem orderItem = new OrderItem();

        orderItem.setOrderItemId(2L);
        orderItem.setProduct(productRepository.getOne(2L));
        orderItem.setOrders(ordersRepository.getOne(2L));
        orderItem.setSku("SKU");
        orderItem.setPrice(22F);
        orderItem.setDiscount(22F);
        orderItem.setQuantity(22);
        orderItem.setCreatedAt(LocalDateTime.now());
        orderItem.setUpdatedAt(LocalDateTime.now());
        orderItem.setContent("Content");

    }
}
