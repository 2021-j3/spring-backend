package com.ecommerce.j3.controller.api;

import com.ecommerce.j3.controller.dto.OrderDto;
import com.ecommerce.j3.controller.dto.OrderItemDto;
import com.ecommerce.j3.domain.entity.OrderItem;
import com.ecommerce.j3.service.OrderApiLogicService;
import com.ecommerce.j3.service.OrderItemApiLogicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@Api(tags = {"05. OrderItem"})
@Slf4j
@RestController
@RequestMapping(value = "/api/orders")
@AllArgsConstructor
public class OrderItemApiController {
    /*
        1. POST -> (장바구니에서) 주문하기 클릭 시, 세션에 저장된 장바구니아이템들을 OrderItem으로 생성
        2. GET  -> (주문서에서) 주문한 상품 불러오기
    */

    private final OrderItemApiLogicService orderItemService;

    // 1. POST -> (장바구니에서) 주문하기 클릭 시, 세션에 저장된 장바구니아이템들을 OrderItem으로 생성
    @ApiOperation(value = "(OrderItem) 1. POST", notes = "(장바구니에서) 주문하기 클릭 시, 세션에 저장된 장바구니아이템들을 OrderItem으로 생성")
    @PostMapping("/{id}/orderItems")
    public ResponseEntity<OrderItemDto.OrderItemApiResponse> createOrderItemService(@PathVariable Long id,
                                                                                    @RequestBody OrderItemDto.OrderItemApiRequest request) {
        try {
            if (request.getOrderId().equals(null)){
                throw new EntityNotFoundException("Not Found orderId");
            }
            else {
                return new ResponseEntity<OrderItemDto.OrderItemApiResponse>(orderItemService.createService(request), HttpStatus.OK);
            }

        } catch (EntityNotFoundException e) {
            log.info("createOrderItem Exception: {}", e);
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    // 2. GET  -> (주문서에서) 주문한 상품 불러오기
    @ApiOperation(value = "(OrderItem) 2. GET", notes = "(주문서에서) 주문한 상품 불러오기")
    @GetMapping("{orderId}/orderItems/{orderItemsId}")
    public ResponseEntity<OrderItemDto.OrderItemApiResponse> findOrderId(@PathVariable Long orderId, @PathVariable Long OrderItemsId) {
        try {
            return new ResponseEntity<OrderItemDto.OrderItemApiResponse>((MultiValueMap<String, String>) orderItemService.findAllService(), HttpStatus.OK);
        } catch (EntityNotFoundException e){
            log.info("findOrderItemsId Exception: {}", e);
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}

