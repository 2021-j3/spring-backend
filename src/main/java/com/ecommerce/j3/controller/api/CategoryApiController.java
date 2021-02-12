package com.ecommerce.j3.controller.api;

import com.ecommerce.j3.controller.dto.BodyData;
import com.ecommerce.j3.controller.dto.CartDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"09. Category"})
@Slf4j
@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryApiController {

    @ApiOperation(value = "카테고리 POST", notes = "카테고리를 한다.")
    @PostMapping("")
        public BodyData<CartDto.CartApiResponse> create(@RequestBody CartDto.CartApiRequest request) {
        return null;
    }

    @ApiOperation(value = "카테고리 GET", notes = "카테고리를 확인한다.")
    @GetMapping("")
        public BodyData<CartDto.CartApiResponse> read(Long id) {
        return null;
    }

    @ApiOperation(value = "카테고리 PUT", notes = "카테고리를 수정한다.")
    @PutMapping("")
        public BodyData<CartDto.CartApiResponse> update(@RequestBody CartDto.CartApiRequest request) {
        return null;
    }
    
    @ApiOperation(value = "카테고리 DELETE", notes = "카테고리를 삭제한다.")
    @DeleteMapping("")
        public BodyData delete(Long id) {
        return null;
    }
}
