package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.OrderDto;
import com.ecommerce.j3.domain.entity.*;
import com.ecommerce.j3.domain.mapper.OrderMapper;
import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.repository.OrderRepository;
import com.ecommerce.j3.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;  // final 은 entity 를 한번만 declare 하게 해줌.
    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    public OrderDto.OrderApiResponse save(OrderDto.OrderApiRequest request){
        Order order = orderMapper.toEntity(request);
        orderRepository.save(order);
        return orderMapper.toDto(order);
    }
    /* 주문 */
    /*
    @Transactional
    public Long order(Long accountId,Long productId,Integer quantity){
        // 조회
        Account account = accountRepository.findOne(accountId);
        Product product = productRepository.findOne(productId);

        // orderItem 생성
        OrderItem orderItem = OrderItem.createOrderItem(product,product.getPrice(),quantity);

        // order 생성
        //Order order = Order.createorder(account,orderItem);


        Order save
        orderRepository.save(order);  // OrderItem 은 Cascade 로 따로 영속시켜주지않아도됨. OrderItem은 Order에서만 가르
        return order.getOrdersId();

    }
    */


    // Cancel
    @Transactional
    public void cancelOrder(Long orderId){
        // 주문 조회
        Order order = orderRepository.findById(orderId).orElseThrow();
        // 주문 취소
        order.cancel();
    }





}
