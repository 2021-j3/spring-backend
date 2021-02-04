package com.ecommerce.j3.controller.api;


import com.ecommerce.j3.controller.dto.BodyData;
import com.ecommerce.j3.controller.dto.OrderDto;
import com.ecommerce.j3.service.AccountService;
import com.ecommerce.j3.service.OrderService;
import com.ecommerce.j3.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Table;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"05. Order"})
@Slf4j
@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderApiController implements CrudInterface<OrderDto.OrderApiRequest, OrderDto.OrderApiResponse> {
    private final OrderService orderService;
    private final ProductService productService;
    private final AccountService accountService;

    @GetMapping("/api/orders/buy")   // 바로 주문
    public CreateBuyOrderResponse buyOrder(@RequestBody @Valid OrderApiController.CreateBuyOrderRequest request) {

         //   long order_id = orderService.order(request.getAccountId(),request.getProductId(),request.getQuantity());

        return new CreateBuyOrderResponse();
    }

    @ApiOperation(value = "Order 추가", notes = "Order를 추가한다.")
    @PostMapping("")
    @Override
    public BodyData<OrderDto.OrderApiResponse> create(@RequestBody OrderDto.OrderApiRequest request) {
        return null;
    }

    @ApiOperation(value = "Order 조회", notes = "Order를 조회한다.")
    @GetMapping("")
    @Override
    public BodyData<OrderDto.OrderApiResponse> read(Long id) {
        return null;
    }

    @ApiOperation(value = "Order 수정", notes = "Order를 수정한다.")
    @PutMapping("")
    @Override
    public BodyData<OrderDto.OrderApiResponse> update(@RequestBody OrderDto.OrderApiRequest request) {
        return null;
    }

    @ApiOperation(value = "Order 삭제", notes = "Order를 삭제한다.")
    @DeleteMapping("")
    @Override
    public BodyData delete(Long id) {
        return null;
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

