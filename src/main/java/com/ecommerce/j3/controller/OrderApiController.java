package com.ecommerce.j3.controller;


import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.Order;
import com.ecommerce.j3.domain.entity.OrderItem;
import com.ecommerce.j3.domain.entity.Product;
import com.ecommerce.j3.service.AccountService;
import com.ecommerce.j3.service.OrderService;
import com.ecommerce.j3.service.ProductService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderApiController {
    private final OrderService orderService;
    private final ProductService productService;
    private final AccountService accountService;

    @GetMapping("/api/orders/buy")   // 바로 주문
    public CreateBuyOrderResponse buyOrder(@RequestBody @Valid OrderApiController.CreateBuyOrderRequest request) {

            long order_id = orderService.order(request.getAccountId(),request.getProductId(),request.getQuantity());

        return new CreateBuyOrderResponse();
    }

    @Data
    static class CreateBuyOrderRequest {
        // session id 어떻게 넘어옴?
        Long accountId;
        Long productId;
        Integer quantity;


    }
    @Data
    static class CreateBuyOrderResponse{
    }
}

