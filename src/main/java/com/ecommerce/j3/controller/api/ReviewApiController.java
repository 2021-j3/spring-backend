package com.ecommerce.j3.controller.api;


import com.ecommerce.j3.controller.dto.BodyData;
import com.ecommerce.j3.controller.dto.ReviewDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"08. Review"})
@Slf4j
@RestController
@RequestMapping("/api/reviews")
@AllArgsConstructor
public class ReviewApiController implements CrudInterface<ReviewDto.ReviewApiRequest, ReviewDto.ReviewApiResponse> {

    @ApiOperation(value = "리뷰 POST", notes = "리뷰를 생성한다.")
    @PostMapping("")
    @Override
    public BodyData<ReviewDto.ReviewApiResponse> create(@RequestBody ReviewDto.ReviewApiRequest request) {
        return null;
    }

    @ApiOperation(value = "리뷰 GET", notes = "리뷰를 불러온다.")
    @GetMapping("")
    @Override
    public BodyData<ReviewDto.ReviewApiResponse> read(Long id) {
        return null;
    }

    @ApiOperation(value = "리뷰 PUT", notes = "리뷰를 수정한다.")
    @PutMapping("")
    @Override
    public BodyData<ReviewDto.ReviewApiResponse> update(@RequestBody ReviewDto.ReviewApiRequest request) {
        return null;
    }

    @ApiOperation(value = "리뷰 DELETE", notes = "리뷰를 삭제한다.")
    @DeleteMapping("")
    @Override
    public BodyData delete(Long id) {
        return null;
    }
}
