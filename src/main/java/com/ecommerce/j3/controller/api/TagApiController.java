package com.ecommerce.j3.controller.api;


import com.ecommerce.j3.controller.dto.BodyData;
import com.ecommerce.j3.controller.dto.TagDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"10. Tag"})
@Slf4j
@RestController
@RequestMapping("/api/tags")
@AllArgsConstructor
public class TagApiController {

    @ApiOperation(value = "태그 POST", notes = "태그를 생성한다.")
    @PostMapping("")
        public BodyData<TagDto.TagApiResponse> create(@RequestBody TagDto.TagApiRequest request) {
        return null;
    }

    @ApiOperation(value = "태그 GET", notes = "태그를 불러온다.")
    @GetMapping("{id}")
        public BodyData<TagDto.TagApiResponse> read(@PathVariable("id") Long id) {
        return null;
    }

    @ApiOperation(value = "태그 PUT", notes = "태그를 수정한다.")
    @PutMapping("")
        public BodyData<TagDto.TagApiResponse> update(@RequestBody TagDto.TagApiRequest request) {
        return null;
    }

    @ApiOperation(value = "태그 DELETE", notes = "태그를 삭제한다.")
    @DeleteMapping("{id}")
        public BodyData delete(@PathVariable("id") Long id) {
        return null;
    }
}
