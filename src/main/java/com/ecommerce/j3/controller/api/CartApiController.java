package com.ecommerce.j3.controller.api;

import com.ecommerce.j3.controller.dto.BodyData;
import com.ecommerce.j3.controller.dto.CartDto.CartApiRequest;
import com.ecommerce.j3.controller.dto.CartDto.CartApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"3. Cart"})
@Slf4j
@RestController
@RequestMapping("/api/carts")
@AllArgsConstructor
public class CartApiController implements CrudInterface<CartApiRequest, CartApiResponse> {

    @ApiOperation(value = "카트 추가", notes = "카트를 추가한다.")
    @PostMapping("")
    @Override
    public BodyData<CartApiResponse> create(CartApiRequest request) {
        return null;
    }

    @ApiOperation(value = "카트 조회", notes = "카트를 조회한다.")
    @GetMapping("")
    @Override
    public BodyData<CartApiResponse> read(Long id) {
        return null;
    }

    @ApiOperation(value = "카트 수정", notes = "카트를 수정한다.")
    @PutMapping("")
    @Override
    public BodyData<CartApiResponse> update(CartApiRequest request) {
        return null;
    }

    @ApiOperation(value = "카트 삭제", notes = "카트를 삭제한다.")
    @DeleteMapping("")
    @Override
    public BodyData delete(Long id) {
        return null;
    }
}
