package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.OrderDto;
import com.ecommerce.j3.domain.entity.*;
import com.ecommerce.j3.domain.mapper.OrderMapper;
import com.ecommerce.j3.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class OrderApiLogicService {
    /* user 정보 확인 */
    private final AccountRepository accountRepository;

    /* 바로 주문하기 눌렀을 때 사용하기 위한 ProductRepository */
    private final ProductRepository productRepository;

    /* Cart의 정보를 가져오기 위한 Repository */
    private final CartItemRepository cartItemRepository;

    /* order 저장을 위한 Repository */
    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    private final OrderMapper orderMapper;

    // GET
    public OrderDto.OrderApiResponse findOrderService(Long id) {
        Order orderFromDB = orderRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Not Found OrderId"));

        return orderMapper.toApiResponse(orderFromDB);
    }

    /* 페이지에서 주문하기 눌렀을 때 Product에서 바로 저장 */
    public OrderDto.OrderItemCreateResponseByProduct saveByProductService(OrderDto.OrderItemCreateRequestByProduct request) {
        /* 1. findProductId */
        Optional<Product> optionalProduct = Optional.ofNullable(
                productRepository.findById(request.getProductId())
                        .orElseThrow(() -> new EntityNotFoundException("Not Found ProductId"))
        );

        Product product = optionalProduct.get();

        /* 2. findAccountId */
        Optional<Account> optionalAccount = Optional.ofNullable(
                accountRepository.findById(request.getAccountId())
                        .orElseThrow(() -> new EntityNotFoundException("Not Found AccountId"))
        );

        Account account = optionalAccount.get();

        /* order Builder */
        Order orderFromDB = new Order();
        orderFromDB.createOrder(account, product);

        orderRepository.save(orderFromDB);
        return orderMapper.toApiProductResponse(orderFromDB);
    }

    /* cartItem에서 가져와서 저장 */
    public OrderDto.OrderItemCreateResponseByCartItem saveByCartItemService(OrderDto.OrderItemCreateRequestByCartItem request) {
        Optional<CartItem> optionalCartItem = Optional.ofNullable(
                cartItemRepository.findById(request.getCartItemId())
                        .orElseThrow(() -> new EntityNotFoundException("Not Found cartItemId"))
        );

        CartItem cartItem = optionalCartItem.get();

        /* 2. findByAccountId */
        Account account = cartItem.getCart().getAccount();
        Long accountId = account.getAccountId();

        if (request.getAccountId().equals(accountId))
        {
            /* order Builder */
            Order orderFromDB = new Order();
            orderFromDB.createOrder(account, cartItem.getProduct());

            /* orderItem Builder */

            orderRepository.save(orderFromDB);
            return orderMapper.toApiCartResponse(orderFromDB);
        }
        else {
            throw new EntityNotFoundException("Not Found AccountId");
        }
    }

    public OrderDto.OrderApiResponse updateUserService(OrderDto.OrderApiRequest request){
        if (accountRepository.getOne(request.getAccount().getAccountId()) == null){
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
        if (accountRepository.getOne(request.getAccount().getAccountId()) == null){
            throw new EntityNotFoundException("Not Found updatePay");
        }
        Order orderFromDB = orderRepository
                .findById(request.getOrdersId())
                .orElseThrow(() -> new EntityNotFoundException());

        Order updateDB = orderMapper.updatePayFromDto(orderFromDB, request);
        orderRepository.save(updateDB);
        return orderMapper.toApiResponse(updateDB);
    }

    public void deleteService(Long id) {
        orderRepository.deleteById(id);
    }


    public OrderDto.OrderApiResponse findByAccountId(Long accountId) {
        Order order = orderRepository.findByAccountId(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Not Found AccountId"));
        return orderMapper.toApiResponse(order);
    }
}