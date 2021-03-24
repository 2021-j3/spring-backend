package com.ecommerce.j3.controller.api;

import com.ecommerce.j3.controller.dto.AccountDto;
import com.ecommerce.j3.controller.dto.BodyData;
import com.ecommerce.j3.controller.dto.OrderDto;
import com.ecommerce.j3.domain.J3UserDetails;
import com.ecommerce.j3.service.AccountApiLogicService;
import com.ecommerce.j3.service.OrderApiLogicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@Api(tags = {"05. Order"})
@Slf4j
@RestController
@RequestMapping(value = "/api/orders")
@AllArgsConstructor
public class OrderApiController {
    /*
        1. POST    -> (장바구니나 상품페이지에서) 주문하기를 눌렀을 때
        2. GET     -> (장바구니나 상품페이지에서) 내 정보에서 확인
        3. PUT     -> (주문내역에서) 배송지 변경할 때
        4. PUT     -> (주문내역에서) 결제 정보를 변경할 때
        5. DELETE  -> 주문 취소 눌렀을 때
        6. GET     -> myPage
    */

    private final OrderApiLogicService orderService;

//    // 1. POST    -> (장바구니나 상품페이지에서) 주문하기를 눌렀을 때
//    @ApiOperation(value = "(Order) 1. POST", notes = "(장바구니나 상품페이지에서) 주문하기를 눌렀을 때")
//    @PostMapping("/{id}")
//    public ResponseEntity<OrderDto.OrderApiResponse> createOrder(@PathVariable Long id,
//            @Valid @RequestBody OrderDto.OrderApiRequest request) {
//
//        // TODO: Token 처리 해야 됨.
//
//        try {
//            if (request.getAccountId().equals(null)){
//                // TODO: redirection login page
//            }
//            else {
//                return new ResponseEntity<OrderDto.OrderApiResponse>(orderService.directSaveService(request), HttpStatus.OK);
//            }
//        } catch (EntityNotFoundException e) {
//            log.info("createOrder Exception: {}", e);
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
//        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//    }

    // 1. POST    -> 상품페이지에서 주문하기를 눌렀을 때
    @ApiOperation(value = "(Order) 1. POST", notes = "상품페이지에서 주문하기를 눌렀을 때")
    @PostMapping("/direct/{id}")
    public ResponseEntity<OrderDto.OrderItemCreateResponseByProduct> createOrderByProduct(@PathVariable Long id,
                                                                 @Valid @RequestBody OrderDto.OrderItemCreateRequestByProduct request) {
        // TODO: Token 처리 해야 됨.
        try {
                return new ResponseEntity<OrderDto.OrderItemCreateResponseByProduct>(orderService.saveByProductService(request), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            log.info("createOrder Exception: {}", e);
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


    // 2. POST    -> 상품페이지에서 주문하기를 눌렀을 때
    @ApiOperation(value = "(Order) 1-1. POST", notes = "장바구니에서 주문하기를 눌렀을 때")
    @PostMapping("/indirect/{id}")
    public ResponseEntity<OrderDto.OrderItemCreateResponseByCartItem> createOrderByProduct(@PathVariable Long id,
                                                                                          @Valid @RequestBody OrderDto.OrderItemCreateRequestByCartItem request) {
        // TODO: Token 처리 해야 됨.
        try {
            return new ResponseEntity<OrderDto.OrderItemCreateResponseByCartItem>(orderService.saveByCartItemService(request), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            log.info("createOrder Exception: {}", e);
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    // 2. GET     -> (장바구니나 상품페이지에서) 내 정보에서 확인, 결제하기 전에 보이는 페이지
    @ApiOperation(value = "(Order) 2. GET", notes = "(장바구니나 상품페이지에서) 내 정보에서 확인")
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto.OrderApiResponse> readOrder(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<OrderDto.OrderApiResponse>(orderService.findOrderService(id), HttpStatus.OK);
        } catch (EntityNotFoundException e){
            log.info("readOrder Exception: {}", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 3. PUT     -> (주문내역에서) 배송지 변경할 때
    @ApiOperation(value = "(Order) 3. PUT", notes = "(주문내역에서) 배송지 변경할 때")
    @PutMapping("/{id}/addresses")
    public ResponseEntity<OrderDto.OrderApiResponse> updateOrderAddress(@Valid @RequestBody OrderDto.OrderApiRequest request, @PathVariable Long id) {
        try{
            return new ResponseEntity<OrderDto.OrderApiResponse>(orderService.updateUserService(request), HttpStatus.OK);
        } catch (EntityNotFoundException e){
            log.info("updateOrderAddress Exception: {}", e);
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    // 4. PUT     -> (주문내역에서) 결제 정보를 변경할 때
    @ApiOperation(value = "(Order) 4. PUT", notes = "(주문내역에서) 결제 정보를 변경할 때")
    @PutMapping("/{id}/prices")
    public ResponseEntity<OrderDto.OrderApiResponse> updateOrderPrice(@Valid @RequestBody OrderDto.OrderApiRequest request, @PathVariable Long id) {
        try{
            return new ResponseEntity<OrderDto.OrderApiResponse>(orderService.updatePayService(request), HttpStatus.OK);
        } catch (EntityNotFoundException e){
            log.info("updateOrderAddress Exception: {}", e);
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    // 5. DELETE  -> 주문 취소 눌렀을 때
    @ApiOperation(value = "(Order) 5. DELETE", notes = "주문 취소 눌렀을 때")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteService(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e){
            log.info("deleteOrder Exception: {}", e);
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    // 6. GET   -> myPage
    @ApiOperation(value = "(Order) 6. GET", notes = "내 Order 정보")
    @GetMapping("/my")
    public ResponseEntity<OrderDto.OrderApiResponse> getMyOrder(Authentication authentication) {
        J3UserDetails userDetails = (J3UserDetails) authentication.getPrincipal();
        Long accountId = userDetails.getAccountId();
        try {
            return new ResponseEntity<OrderDto.OrderApiResponse>(orderService.findByAccountId(accountId), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

