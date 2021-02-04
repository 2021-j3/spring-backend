package com.ecommerce.j3.controller.api;


import com.ecommerce.j3.controller.dto.BodyData;
import com.ecommerce.j3.controller.dto.ProductDto.ProductApiRequest;
import com.ecommerce.j3.controller.dto.ProductDto.ProductApiResponse;
import com.ecommerce.j3.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@Api(tags = {"02. Product"})
@RestController
@RequestMapping("/api/Product")
@AllArgsConstructor
public class ProductApiController implements CrudInterface<ProductApiRequest, ProductApiResponse> {
    private final ProductService productService;

    @ApiOperation(value = "제품 추가", notes="제품를 추가한다")
    @PostMapping("")
    @Override
    public BodyData<ProductApiResponse> create(@RequestBody ProductApiRequest request) {
        productService.save(request);
        return null;
    }

    @ApiOperation(value = "제품 일기", notes = "제품를 가져온다")
    @Override
//    @GetMapping
    public BodyData<ProductApiResponse> read(Long id) {
        try {
            return BodyData.OK(productService.findOne(id));
        }catch (EntityNotFoundException e){
            return BodyData.ERROR("데이터가 없습니다");
        }
    }

    @ApiOperation(value = "제품 갱신", notes = "제품를 갱신한다.")
    @Override
    @PutMapping
    public BodyData<ProductApiResponse> update(@RequestBody ProductApiRequest request) {
        try{
            return BodyData.OK(productService.update(request));
        }catch (EntityNotFoundException e){
            return BodyData.ERROR("데이터가 없습니다");
        }
    }

    @ApiOperation(value = "제품 삭제", notes = "제품를 삭제한다.")
    @Override
    @DeleteMapping
    public BodyData delete(Long id) {
        try{
            productService.remove(id);
            return BodyData.OK();
        }catch (EntityNotFoundException e){
            return BodyData.ERROR("데이터가 없습니다");
        }
    }
}
