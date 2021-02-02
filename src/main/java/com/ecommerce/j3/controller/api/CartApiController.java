package com.ecommerce.j3.controller.api;

import com.ecommerce.j3.controller.dto.BodyData;
import com.ecommerce.j3.controller.dto.CartDto.CartApiRequest;
import com.ecommerce.j3.controller.dto.CartDto.CartApiResponse;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"3. Cart"})
@Slf4j
@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class CartApiController implements CrudInterface<CartApiRequest, CartApiResponse> {
    @Override
    public BodyData<CartApiResponse> create(CartApiRequest request) {
        return null;
    }

    @Override
    public BodyData<CartApiResponse> read(Long id) {
        return null;
    }

    @Override
    public BodyData<CartApiResponse> update(CartApiRequest request) {
        return null;
    }

    @Override
    public BodyData delete(Long id) {
        return null;
    }
}
