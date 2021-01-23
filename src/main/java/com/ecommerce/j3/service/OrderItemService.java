package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.OrderItem;
import com.ecommerce.j3.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {
    private OrderItemRepository ordersItemsRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemsRepository){this.ordersItemsRepository = orderItemsRepository;}

    public OrderItem save(OrderItem orderItems){
        ordersItemsRepository.save(orderItems);
        return orderItems;
    }

    public OrderItem update(OrderItem orderItems){
        ordersItemsRepository.save(orderItems);
        return orderItems;
    }

    public Optional<OrderItem> findOne(Long orderItemId){
        return ordersItemsRepository.findById(orderItemId);
    }

    public List<OrderItem> findAll(){
        return ordersItemsRepository.findAll();
    }

    public void remove(OrderItem orderItem){
        ordersItemsRepository.delete(orderItem);
    }
}
