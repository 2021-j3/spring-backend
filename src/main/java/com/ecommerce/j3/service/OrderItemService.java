package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.OrderItemDto;
import com.ecommerce.j3.domain.entity.OrderItem;
import com.ecommerce.j3.domain.entity.OrderItem;
import com.ecommerce.j3.domain.mapper.OrderItemMapper;
import com.ecommerce.j3.repository.OrderItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderItemService {
    private OrderItemRepository orderItemsRepository;
    private final OrderItemMapper orderItemMapper;

    public OrderItemDto.OrderItemApiResponse save(OrderItemDto.OrderItemApiRequest request){
        OrderItem orderItem = orderItemMapper.toEntity(request);
        orderItemsRepository.save(orderItem);
        return orderItemMapper.toDto(orderItem);
    }

    public OrderItem save(OrderItem orderItems){
        orderItemsRepository.save(orderItems);
        return orderItems;
    }

    public OrderItem update(OrderItem orderItems){
        orderItemsRepository.save(orderItems);
        return orderItems;
    }

    public Optional<OrderItem> findOne(Long orderItemId){
        return orderItemsRepository.findById(orderItemId);
    }

    public List<OrderItem> findAll(){
        return orderItemsRepository.findAll();
    }

    public void remove(OrderItem orderItem){
        orderItemsRepository.delete(orderItem);
    }
}
