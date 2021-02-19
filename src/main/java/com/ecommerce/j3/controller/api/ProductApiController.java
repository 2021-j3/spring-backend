package com.ecommerce.j3.controller.api;


import com.ecommerce.j3.controller.dto.BodyData;
import com.ecommerce.j3.controller.dto.ProductDto;
import com.ecommerce.j3.controller.dto.ProductDto.ProductApiRequest;
import com.ecommerce.j3.controller.dto.ProductDto.ProductApiResponse;
import com.ecommerce.j3.controller.dto.ProductDto.SearchCondition;
import com.ecommerce.j3.controller.dto.ProductDto.ProductApiResponsePage;
import com.ecommerce.j3.service.ProductApiLogicService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Api(tags = {"02. Product"})
@RestController
//@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductApiController {
    private final ProductApiLogicService productApiLogicService;

    @ApiOperation(value = "제품 추가", notes = "제품를 추가한다")
    @PostMapping("/api/products")
    public ResponseEntity<ProductApiResponse> create(@RequestBody ProductApiRequest request) {
        return ResponseEntity.ok(productApiLogicService.saveProduct(request));
    }

    @ApiOperation(value = "제품 읽기1", notes = "조건에 맞는 제품을 가져온다")
    @GetMapping("/api/products")
    public ResponseEntity<ProductApiResponsePage> searchProduct(
            @RequestParam(value = "query", required = false) String query,
            @RequestParam(value = "minPrice", required = false) Integer minPrice,
            @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
            @RequestParam(value = "categories", required = false) List<Long> categories,
            @RequestParam(value = "tags", required = false) List<Long> tags,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "100") Integer size,
            @RequestParam(value = "order", required = false, defaultValue = "ASC") String order,
            @RequestParam(value = "by", required = false, defaultValue = "productId") String by) {
        SearchCondition searchCondition = SearchCondition.builder()
                .query(query)
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .categoryIds(categories)
                .tagIds(tags).build();
        // 2021-02-18 페이지네이션 처리
        page = page < 0 ? 0 : page == 0 ? page : page-1;
        final Stream<String> allowed_criteria = Arrays.stream(new String[]{"productId", "title", "price", "createdAt"});
        Pageable pageable = PageRequest.of(page, size, Sort.by(
                order.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC,
                allowed_criteria.anyMatch(by::equals) ? by : "productId")); // 2021-02-18 필드 이름 문제 해결
        return ResponseEntity.ok(productApiLogicService.searchProducts(searchCondition, pageable));
    }

    @ApiOperation(value = "제품 읽기2", notes = "제품를 가져온다")
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
    public BodyData<ProductApiResponse> update(@PathVariable("id") Long id, @RequestBody ProductApiRequest request) {
        try {
            return BodyData.OK(productApiLogicService.updateProduct(request));
        } catch (EntityNotFoundException e) {
            return BodyData.ERROR("데이터가 없습니다");
        }
    }

    @ApiOperation(value = "제품 삭제", notes = "제품를 삭제한다.")
    @DeleteMapping("/api/products/{id}")
    public BodyData delete(@PathVariable("id") Long id) {
        try {
            productApiLogicService.removeProduct(id);
            return BodyData.OK();
        } catch (EntityNotFoundException e) {
            return BodyData.ERROR("데이터가 없습니다");
        }
    }

}
