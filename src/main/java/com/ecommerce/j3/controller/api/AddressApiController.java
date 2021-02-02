package com.ecommerce.j3.controller.api;

import com.ecommerce.j3.controller.dto.AddressDto.AddressApiRequest;
import com.ecommerce.j3.controller.dto.AddressDto.AddressApiResponse;
import com.ecommerce.j3.controller.dto.BodyData;
import com.ecommerce.j3.service.AccountApiLogicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 1. first

@Api(tags = {"2. Address"})
@RestController
@RequestMapping("/api/addresses")
@AllArgsConstructor
public class AddressApiController implements CrudInterface<AddressApiRequest, AddressApiResponse> {

    AccountApiLogicService accountApiLogicService;

    @ApiOperation(value = "주소 추가", notes="주소를 추가한다")
    @PostMapping("")
    @Override
    public BodyData<AddressApiResponse> create(AddressApiRequest request) {
        System.out.println("test");
        return null;
    }

    @Override
    public BodyData<AddressApiResponse> read(Long id) {
        return null;
    }

    @Override
    public BodyData<AddressApiResponse> update(AddressApiRequest request) {
        return null;
    }

    @Override
    public BodyData delete(Long id) {
        return null;
    }
}
