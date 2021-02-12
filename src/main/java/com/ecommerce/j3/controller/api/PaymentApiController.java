package com.ecommerce.j3.controller.api;

import com.ecommerce.j3.controller.dto.BodyData;
import com.ecommerce.j3.controller.dto.PaymentDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"07. Payment"})
@Slf4j
@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
public class PaymentApiController {

    @ApiOperation(value = "결제 POST", notes = "결제를 한다.")
    @PostMapping("")
        public BodyData<PaymentDto.PaymentApiResponse> create(@RequestBody PaymentDto.PaymentApiRequest request) {
        return null;
    }

    @ApiOperation(value = "결제 GET", notes = "결제를 확인한다.")
    @GetMapping("")
        public BodyData<PaymentDto.PaymentApiResponse> read(Long id) {
        return null;
    }

    @ApiOperation(value = "결제 PUT", notes = "결제를 수정한다.")
    @PutMapping("")
        public BodyData<PaymentDto.PaymentApiResponse> update(@RequestBody PaymentDto.PaymentApiRequest request) {
        return null;
    }

    @ApiOperation(value = "결제 DELETE", notes = "결제를 삭제한다.")
    @DeleteMapping("")
        public BodyData delete(Long id) {
        return null;
    }
}
