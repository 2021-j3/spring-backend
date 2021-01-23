package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.Order;
import com.ecommerce.j3.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository){this.orderRepository = orderRepository;}

    public Order save(Order order){
        orderRepository.save(order);
        return order;
    }

    public Order update(Order order){
        orderRepository.save(order);
        return order;
    }

    public Optional<Order> findOne(Long orderId){
        return orderRepository.findById(orderId);
    }

    public List<Order> findByAccount(Account account){return orderRepository.findByAccount(account);}

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public void remove(Order order){
        orderRepository.delete(order);
    }
}
