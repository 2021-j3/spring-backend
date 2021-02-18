package com.ecommerce.j3.controller.api;

import com.ecommerce.j3.controller.dto.BodyData;
import com.ecommerce.j3.controller.dto.CategoryDto;
import com.ecommerce.j3.service.CategoryApiLogicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"09. Category"})
@Slf4j
@RestController
//@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryApiController {
    private final CategoryApiLogicService categoryApiLogicService;

    @ApiOperation(value = "카테고리 POST", notes = "카테고리를 한다.")
    @PostMapping("/api/categories")
        public BodyData<CategoryDto.CategoryApiResponse> create(@RequestBody CategoryDto.CategoryApiRequest request) {
        return null;
    }

    @ApiOperation(value = "카테고리 GET", notes = "카테고리를 확인한다.")
    @GetMapping("/api/categories/{id}")
        public BodyData<CategoryDto.CategoryApiResponse> read(@PathVariable("id") Long id) {
        return null;
    }

    @ApiOperation(value = "카테고리 GET", notes = "카테고리를 확인한다.")
    @GetMapping("/api/categories")
    public ResponseEntity<List<CategoryDto.CategoryApiResponse>> readAll() {
        return ResponseEntity.ok(categoryApiLogicService.findAllCategory());
    }
    
    @ApiOperation(value = "카테고리 PUT", notes = "카테고리를 수정한다.")
    @PutMapping("/api/categories")
        public BodyData<CategoryDto.CategoryApiResponse> update(@RequestBody CategoryDto.CategoryApiRequest request) {
        return null;
    }
    
    @ApiOperation(value = "카테고리 DELETE", notes = "카테고리를 삭제한다.")
    @DeleteMapping("/api/categories/{id}")
        public BodyData delete(@PathVariable("id") Long id) {
        return null;
    }
}
