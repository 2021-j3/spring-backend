package com.ecommerce.j3.service;

import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.repository.OrderRepository;
import com.ecommerce.j3.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class OrderApiLogicService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final AccountRepository accountRepository;

//    Product product = productRepository.findOne(10l);
//    Product product1 = productRepository.findOne(11l);
//
//    OrderItem orderItem = OrderItem.createOrderItem(product,product.getPrice(),1);
//    orderItem.
//
//
//            OrderItem orderItem1 = OrderItem.createOrderItem(product1,product1.getPrice(),1);
//        orderItem1.setSku("sss");
//
//    Account account1 = accountRepository.findOne(1l);
//
//        orderRepository.makeOrder(account1,orderItem,orderItem1);
}