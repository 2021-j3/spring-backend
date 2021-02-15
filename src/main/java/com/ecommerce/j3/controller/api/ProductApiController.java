package com.ecommerce.j3.controller.api;


import com.ecommerce.j3.controller.dto.BodyData;
import com.ecommerce.j3.controller.dto.ProductDto.ProductApiRequest;
import com.ecommerce.j3.controller.dto.ProductDto.ProductApiResponse;
import com.ecommerce.j3.service.ProductApiLogicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Api(tags = {"02. Product"})
@RestController
//@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductApiController {
    private final ProductApiLogicService productApiLogicService;

    @ApiOperation(value = "제품 추가", notes = "제품를 추가한다")
    @PostMapping("/api/products")
    public BodyData<ProductApiResponse> create(@RequestBody ProductApiRequest request) {
        productApiLogicService.saveProduct(request);
        return null;
    }
    @ApiOperation(value = "제품 모두 읽기", notes = "제품을 모두 가져온다")
    @GetMapping("/api/products")
    public BodyData<List<ProductApiResponse>> read() {
        try {
            return BodyData.OK(productApiLogicService.findAllProduct());
        } catch (EntityNotFoundException e) {
            return BodyData.ERROR("데이터가 없습니다");
        }
    }

    @ApiOperation(value = "제품 일기", notes = "제품를 가져온다")
    @GetMapping("/api/products/{id}")
    public BodyData<ProductApiResponse> read(@PathVariable("id") Long id) {
        try {
            return BodyData.OK(productApiLogicService.findProduct(id));
        } catch (EntityNotFoundException e) {
            return BodyData.ERROR("데이터가 없습니다");
        }
    }

    @ApiOperation(value = "제품 갱신", notes = "제품를 갱신한다.")
    @PutMapping("/api/products/{id}")
    public BodyData<ProductApiResponse> update(@RequestBody ProductApiRequest request) {
        try {
            return BodyData.OK(productApiLogicService.updateProduct(request));
        } catch (EntityNotFoundException e) {
            return BodyData.ERROR("데이터가 없습니다");
        }
    }

    @ApiOperation(value = "제품 삭제", notes = "제품를 삭제한다.")
    @DeleteMapping("/api/products")
    public BodyData delete(Long id) {
        try {
            productApiLogicService.removeProduct(id);
            return BodyData.OK();
        } catch (EntityNotFoundException e) {
            return BodyData.ERROR("데이터가 없습니다");
        }
    }
}
