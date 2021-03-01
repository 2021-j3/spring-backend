package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.OrderDto;
import com.ecommerce.j3.domain.entity.CartItem;
import com.ecommerce.j3.domain.entity.Order;
import com.ecommerce.j3.domain.entity.OrderItem;
import com.ecommerce.j3.domain.mapper.OrderMapper;
import com.ecommerce.j3.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@Transactional
//@RequiredArgsConstructor
@Service
public class OrderApiLogicService {
    /* user 정보 확인 */
    private final AccountRepository accountRepository;

    /* 바로 주문하기 눌렀을 때 사용하기 위한 ProductRepository */
    private final ProductRepository productRepository;

    /* CartItem의 정보를 가져오기 위한 Repository */
    private final CartItemRepository cartItemRepository;

    /* order 저장을 위한 Repository */
    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    private OrderMapper orderMapper;

    public OrderApiLogicService(AccountRepository accountRepository, ProductRepository productRepository,
                                CartItemRepository cartItemRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository){
        this.accountRepository = accountRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    /* (상품페이지에서 주문하기 눌렀을 때) Product에서 바로 저장 */
    public OrderDto.OrderApiResponse directSaveService(OrderDto.OrderApiRequest request) {
        Order order = orderMapper.toEntity(request);

        orderRepository.save(order);
        return orderMapper.toApiResponse(order);
    }

    /* (장바구니에서 주문하기 눌렀을 때) CartItem에서 가져와서 저장*/
    public OrderDto.OrderApiResponse indirectSaveService(OrderDto.OrderApiRequest request) {
        Order order = orderMapper.toEntity(request);
        List<CartItem> cartItems = cartItemRepository.findAll();

        orderRepository.save(order);
        return orderMapper.toApiResponse(order);
    }

    public OrderDto.OrderApiResponse updateUserService(OrderDto.OrderApiRequest request){
        if (accountRepository.getOne(request.getAccountId()) == null){
            throw new EntityNotFoundException("Not Found updateUser");
        }

        Order orderFromDB = orderRepository
                .findById(request.getOrdersId())
                .orElseThrow(() -> new EntityNotFoundException());

        Order updateDB = orderMapper.updateUserFromDto(orderFromDB, request);
        orderRepository.save(updateDB);
        return orderMapper.toApiResponse(updateDB);

    }

    public OrderDto.OrderApiResponse updatePayService(OrderDto.OrderApiRequest request){
        if (accountRepository.getOne(request.getAccountId()) == null){
            throw new EntityNotFoundException("Not Found updatePay");
        }
        Order orderFromDB = orderRepository
                .findById(request.getOrdersId())
                .orElseThrow(() -> new EntityNotFoundException());

        Order updateDB = orderMapper.updatePayFromDto(orderFromDB, request);
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

    public void deleteService(Long id) {
        orderRepository.deleteById(id);
    }

}