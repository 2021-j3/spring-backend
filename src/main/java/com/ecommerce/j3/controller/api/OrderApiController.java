package com.ecommerce.j3.controller.api;

import com.ecommerce.j3.controller.dto.BodyData;
import com.ecommerce.j3.controller.dto.OrderDto;
//import com.ecommerce.j3.service.AccountApiLogicService;
import com.ecommerce.j3.service.OrderApiLogicService;
import com.ecommerce.j3.service.ProductApiLogicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@Api(tags = {"05. Order"})
@Slf4j
@RestController
@RequestMapping(value = "/api/orders")
@AllArgsConstructor
public class OrderApiController { // implements ControllerCrudInterface<OrderDto.OrderApiRequest, OrderDto.OrderApiResponse> {
    /*
        1. POST    -> (장바구니나 상품페이지에서) 주문하기를 눌렀을 때
        2. GET     -> (장바구니나 상품페이지에서) 내 정보에서 확인
        3. PUT     -> (주문내역에서) 배송지 변경할 때
        4. PUT     -> (주문내역에서) 결제 정보를 변경할 때
        5. DELETE  -> 주문 취소 눌렀을 때
    */

    private final OrderApiLogicService orderService;
//    private final ProductApiLogicService productApiLogicService;
//    private final AccountService accountService;

    // 1. POST    -> (장바구니나 상품페이지에서) 주문하기를 눌렀을 때
    @ApiOperation(value = "(Order) 1. POST", notes = "(장바구니나 상품페이지에서) 주문하기를 눌렀을 때")
    @PostMapping("/")
    public ResponseEntity<OrderDto.OrderApiResponse> createOrder(@Valid @RequestBody OrderDto.OrderApiRequest request) {
        try {
            if (request.getAccountId() == null){
                // TODO: redirection login page
            }
            else {
                log.info("success createOrder request: {}", request);
                return new ResponseEntity<OrderDto.OrderApiResponse>(orderService.saveService(request), HttpStatus.OK);
            }
        } catch (EntityNotFoundException e) {
            log.info("createOrder Exception: {}", e);
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        return null;
    }

    // 2. GET     -> (장바구니나 상품페이지에서) 내 정보에서 확인
    @ApiOperation(value = "(Order) 2. GET", notes = "(장바구니나 상품페이지에서) 내 정보에서 확인")
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto.OrderApiResponse> findOrderId(@PathVariable Long id) {
        try {
            log.info("success orderId: {}", id);
            return new ResponseEntity<OrderDto.OrderApiResponse>(orderService.findIdService(id), HttpStatus.OK);
        } catch (EntityNotFoundException e){
            log.info("findOrderId Exception: {}", e);
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    // 3. PUT     -> (주문내역에서) 배송지 변경할 때
    @ApiOperation(value = "(Order) 3. PUT", notes = "(주문내역에서) 배송지 변경할 때")
    @PutMapping("/addresses")
    public ResponseEntity<OrderDto.OrderApiResponse> updateOrderAddress(@Valid @RequestBody OrderDto.OrderApiRequest request) {
        try{
            log.info("success updateOrderAddress request: {}", request);
            return new ResponseEntity<OrderDto.OrderApiResponse>(orderService.updateService(request), HttpStatus.OK); // 여기 에러
        } catch (EntityNotFoundException e){
            log.info("updateOrderAddress Exception: {}", e);
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    // 4. PUT     -> (주문내역에서) 결제 정보를 변경할 때
    @ApiOperation(value = "(Order) 4. PUT", notes = "(주문내역에서) 결제 정보를 변경할 때")
    @PutMapping("/prices")
    public ResponseEntity<OrderDto.OrderApiResponse> updateOrderPrice(@Valid @RequestBody OrderDto.OrderApiRequest request) {
        try{
            log.info("success updateOrderPrice request: {}", request);
            return new ResponseEntity<OrderDto.OrderApiResponse>(orderService.updateService(request), HttpStatus.OK); // 여기 에러
        } catch (EntityNotFoundException e){
            log.info("updateOrderAddress Exception: {}", e);
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    // 5. DELETE  -> 주문 취소 눌렀을 때
    @ApiOperation(value = "(Order) 5. DELETE", notes = "주문 취소 눌렀을 때")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrder(@PathVariable Long id) {
        try {
            orderService.removeService(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (EntityNotFoundException e){
            log.info("deleteOrder Exception: {}", e);
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }
}

