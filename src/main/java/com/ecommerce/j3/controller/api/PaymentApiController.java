package com.ecommerce.j3.controller.api;

import com.ecommerce.j3.controller.dto.BodyData;
import com.ecommerce.j3.controller.dto.OrderDto;
import com.ecommerce.j3.controller.dto.PaymentDto;
import com.ecommerce.j3.repository.OrderRepository;
import com.ecommerce.j3.repository.PaymentRepository;
import com.ecommerce.j3.service.OrderApiLogicService;
import com.ecommerce.j3.service.PaymentApiLogicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@Api(tags = {"07. Payment"})
@Slf4j
@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
public class PaymentApiController{
    /*
        1. POST    -> Order에서 주문을 완료했을 때

    */

    private final PaymentApiLogicService paymentService;

    // 1. POST    -> Order에서 주문을 완료했을 때
    @ApiOperation(value = "(Payment) 1. POST", notes = "Order에서 주문을 완료했을 때")
    @PostMapping("")
    public ResponseEntity<PaymentDto.PaymentApiResponse> createPayment(@Valid @RequestBody PaymentDto.PaymentApiRequest request) {
        try {
            if (request.getOrderId() == null){
                // TODO: redirection login page
            }
            else {
                log.info("success createPayment request: {}", request);
                return new ResponseEntity<PaymentDto.PaymentApiResponse>(paymentService.saveService(request), HttpStatus.OK);
            }
        } catch (EntityNotFoundException e) {
            log.info("createOrder Exception: {}", e);
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        return null;
    }
//
//    @ApiOperation(value = "결제 GET", notes = "결제를 확인한다.")
//    @GetMapping("")
//    @Override
//    public BodyData<PaymentDto.PaymentApiResponse> read(Long id) {
//        return null;
//    }
//
//    @ApiOperation(value = "결제 PUT", notes = "결제를 수정한다.")
//    @PutMapping("")
//    @Override
//    public BodyData<PaymentDto.PaymentApiResponse> update(@RequestBody PaymentDto.PaymentApiRequest request) {
//        return null;
//    }
//
//    @ApiOperation(value = "결제 DELETE", notes = "결제를 삭제한다.")
//    @DeleteMapping("")
//    @Override
//    public BodyData delete(Long id) {
//        return null;
//    }
}
