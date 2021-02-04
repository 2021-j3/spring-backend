package com.ecommerce.j3.controller.api;


import com.ecommerce.j3.controller.dto.AddressDto;
import com.ecommerce.j3.controller.dto.AddressDto.AddressApiRequest;
import com.ecommerce.j3.controller.dto.AddressDto.AddressApiResponse;
import com.ecommerce.j3.controller.dto.BodyData;
import com.ecommerce.j3.service.AddressService;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"02. Address"})
@RestController
@RequestMapping("/api/addresses")
@AllArgsConstructor
public class AddressApiController implements CrudInterface<AddressApiRequest, AddressApiResponse> {
    private final AddressService addressService;
  
    @ApiOperation(value = "주소 추가", notes="주소를 추가한다")
    @PostMapping("")
    @Override
    public BodyData<AddressApiResponse> create(@RequestBody AddressApiRequest request) {
        System.out.println("test");
        return null;
    }

    @ApiOperation(value = "주소 일기", notes = "주소를 가져온다")
    @Override
//    @GetMapping
    public BodyData<AddressApiResponse> read(Long id) {
        return null;
    }

    @ApiOperation(value = "주소 갱신", notes = "주소를 갱신한다.")
    @Override
    @PutMapping
    public BodyData<AddressApiResponse> update(@RequestBody AddressApiRequest request) {
        return null;
    }

    @ApiOperation(value = "주소 삭제", notes = "주소를 삭제한다.")
    @Override
    @DeleteMapping
    public BodyData delete(Long id) {
        return null;
    }
}
