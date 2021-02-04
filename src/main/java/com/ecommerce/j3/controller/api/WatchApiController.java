package com.ecommerce.j3.controller.api;


import com.ecommerce.j3.controller.dto.BodyData;
import com.ecommerce.j3.controller.dto.WatchDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"11. Watch"})
@Slf4j
@RestController
@RequestMapping("/api/watchs")
@AllArgsConstructor
public class WatchApiController implements CrudInterface<WatchDto.WatchApiRequest, WatchDto.WatchApiResponse> {

    @ApiOperation(value = "최근본항목 POST", notes = "최근본항목를 생성한다.")
    @PostMapping("")
    @Override
    public BodyData<WatchDto.WatchApiResponse> create(@RequestBody WatchDto.WatchApiRequest request) {
        return null;
    }

    @ApiOperation(value = "최근본항목 GET", notes = "최근본항목를 불러온다.")
    @GetMapping("")
    @Override
    public BodyData<WatchDto.WatchApiResponse> read(Long id) {
        return null;
    }

    @ApiOperation(value = "최근본항목 PUT", notes = "최근본항목를 수정한다.")
    @PutMapping("")
    @Override
    public BodyData<WatchDto.WatchApiResponse> update(@RequestBody WatchDto.WatchApiRequest request) {
        return null;
    }

    @ApiOperation(value = "최근본항목 DELETE", notes = "최근본항목를 삭제한다.")
    @DeleteMapping("")
    @Override
    public BodyData delete(Long id) {
        return null;
    }
}
