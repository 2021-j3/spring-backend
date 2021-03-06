package com.ecommerce.j3.controller.api;


import com.ecommerce.j3.controller.dto.BodyData;
import com.ecommerce.j3.controller.dto.SuggestionDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"13. Suggestion"})
@Slf4j
@RestController
@RequestMapping("/api/suggestions")
@AllArgsConstructor
public class SuggestionApiController {

    @ApiOperation(value = "추천 POST", notes = "추천를 생성한다.")
    @PostMapping("")
        public BodyData<SuggestionDto.SuggestionApiResponse> create(@RequestBody SuggestionDto.SuggestionApiRequest request) {
        return null;
    }

    @ApiOperation(value = "추천 GET", notes = "추천를 불러온다.")
    @GetMapping("{id}")
        public BodyData<SuggestionDto.SuggestionApiResponse> read(@PathVariable("id") Long id) {
        return null;
    }

    @ApiOperation(value = "추천 PUT", notes = "추천를 수정한다.")
    @PutMapping("")
        public BodyData<SuggestionDto.SuggestionApiResponse> update(@RequestBody SuggestionDto.SuggestionApiRequest request) {
        return null;
    }

    @ApiOperation(value = "추천 DELETE", notes = "추천를 삭제한다.")
    @DeleteMapping("{id}")
        public BodyData delete(@PathVariable("id") Long id) {
        return null;
    }
}
