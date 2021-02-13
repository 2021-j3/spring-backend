package com.ecommerce.j3.controller.api;


import com.ecommerce.j3.controller.dto.BodyData;
import com.ecommerce.j3.controller.dto.CartDto;
import com.ecommerce.j3.service.CartApiLogicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"03. Cart"})
@Slf4j
@RestController
@RequestMapping("/api/carts")
@AllArgsConstructor
public class CartApiController {
    private final CartApiLogicService cartService;

    @ApiOperation(value = "카트 추가", notes = "카트를 추가한다.")
    @PostMapping
    public BodyData<CartDto.CartApiResponse> create(@RequestBody CartDto.CartApiRequest request) {
        CartDto.CartApiResponse cartApiResponse = cartService.save(request);
        return BodyData.OK(cartApiResponse);
    }

    @ApiOperation(value = "카트 조회", notes = "카트를 조회한다.")
    @GetMapping("")
    //    @GetMapping
    public BodyData<CartDto.CartApiResponse> read(Long id) {
        return null;
    }

    @ApiOperation(value = "카트 갱신", notes = "카트를 갱신한다.")
    @PutMapping
    public BodyData<CartDto.CartApiResponse> update(@RequestBody CartDto.CartApiRequest request) {
        return null;
    }

    @ApiOperation(value = "카트 삭제", notes = "카트를 삭제한다.")
    @DeleteMapping
    public BodyData delete(Long id) {
        return null;
    }
}
