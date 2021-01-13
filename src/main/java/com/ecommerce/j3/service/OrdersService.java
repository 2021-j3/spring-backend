package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.*;
import com.ecommerce.j3.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {
    private OrdersRepository ordersRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository){this.ordersRepository = ordersRepository;}

    public Order save(Order order){
        ordersRepository.save(order);
        return order;
    }

    public Order update(Order order){
        ordersRepository.save(order);
        return order;
    }

    public Optional<Order> findOne(Long orderId){
        return ordersRepository.findById(orderId);
    }

    public List<Order> findByAccount(Account account){return ordersRepository.findByAccount(account);}

    public List<Order> findAll(){
        return ordersRepository.findAll();
    }

    public void remove(Order order){
        ordersRepository.delete(order);
    }
}
