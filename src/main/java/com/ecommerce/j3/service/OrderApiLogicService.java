package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.OrderDto;
import com.ecommerce.j3.domain.entity.Order;
import com.ecommerce.j3.domain.mapper.OrderMapper;
import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.repository.OrderRepository;
import com.ecommerce.j3.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class OrderApiLogicService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final AccountRepository accountRepository;
    private final OrderMapper orderMapper;

    public OrderDto.OrderApiResponse updateService(OrderDto.OrderApiRequest request) {
//        if (accountRepository.getOne(request.getAccountId()) == null){
//            new ResponseEntity(HttpStatus.FORBIDDEN);
//            return null;
//        }

        Order orderFromDB = orderRepository
                .findById(request.getOrdersId())
                .orElseThrow(() -> new EntityNotFoundException());

        Order updateDB = orderMapper.updateFromDto(orderFromDB, request);
        orderRepository.save(updateDB);
        return orderMapper.toApiResponse(updateDB);

    }

    public OrderDto.OrderApiResponse findIdService(Long id) {
        // 1. id -> repository getOne / getById
        Order orderFromDB = orderRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        // 2. return account -> accountApiResponse
        return orderMapper.toApiResponse(orderFromDB);
    }

    public OrderDto.OrderApiResponse saveService(OrderDto.OrderApiRequest request) {
        Order order = orderMapper.toEntity(request);

        orderRepository.save(order);
        return orderMapper.toApiResponse(order);
    }


    public void removeService(Long id) {
        orderRepository.deleteById(id);
    }

}