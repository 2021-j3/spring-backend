package com.ecommerce.j3.controller.api;


import com.ecommerce.j3.controller.dto.BodyData;
import com.ecommerce.j3.controller.dto.ReviewDto;
import com.ecommerce.j3.service.ReviewApiLogicService;
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
public class ReviewApiController {
    private final ReviewApiLogicService reviewService;

    @ApiOperation(value = "리뷰 POST", notes = "리뷰를 생성한다.")
    @PostMapping("")
    public BodyData<ReviewDto.ReviewApiResponse> create(@RequestBody ReviewDto.ReviewApiRequest request) {
        return BodyData.OK(reviewService.saveReview(request));
    }

    @ApiOperation(value = "리뷰 GET", notes = "리뷰를 불러온다.")
    @GetMapping("{id}")
        public BodyData<ReviewDto.ReviewApiResponse> read(@PathVariable("id") Long id) {
        return null;
    }

    @ApiOperation(value = "리뷰 PUT", notes = "리뷰를 수정한다.")
    @PutMapping("")
        public BodyData<ReviewDto.ReviewApiResponse> update(@RequestBody ReviewDto.ReviewApiRequest request) {
        return null;
    }

    @ApiOperation(value = "리뷰 DELETE", notes = "리뷰를 삭제한다.")
    @DeleteMapping("{id}")
        public BodyData delete(@PathVariable("id") Long id) {
        return null;
    }
}