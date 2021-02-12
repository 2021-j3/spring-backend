package com.ecommerce.j3.controller.api;


import com.ecommerce.j3.controller.dto.BodyData;
import com.ecommerce.j3.controller.dto.AddressDto.AddressApiRequest;
import com.ecommerce.j3.controller.dto.AddressDto.AddressApiResponse;
import com.ecommerce.j3.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@Api(tags = {"02. Address"})
@RestController
@RequestMapping("/api/Address")
@AllArgsConstructor
public class AddressApiController{
    private final AddressService addressService;

    @ApiOperation(value = "주소 추가", notes="주소를 추가한다")
    @PostMapping("")
    public BodyData<AddressApiResponse> create(@RequestBody AddressApiRequest request) {
        addressService.save(request);
        return null;
    }

    @ApiOperation(value = "주소 일기", notes = "주소를 가져온다")
    @GetMapping
    public BodyData<AddressApiResponse> read(Long id) {
        try {
            return BodyData.OK(addressService.findOne(id));
        }catch (EntityNotFoundException e){
            return BodyData.ERROR("데이터가 없습니다");
        }
    }

    @ApiOperation(value = "주소 갱신", notes = "주소를 갱신한다.")
    @PutMapping
    public BodyData<AddressApiResponse> update(@RequestBody AddressApiRequest request) {
        try{
            return BodyData.OK(addressService.update(request));
        }catch (EntityNotFoundException e){
            return BodyData.ERROR("데이터가 없습니다");
        }
    }

    @ApiOperation(value = "주소 삭제", notes = "주소를 삭제한다.")
    @DeleteMapping
    public BodyData delete(Long id) {
        try{
            addressService.remove(id);
            return BodyData.OK();
        }catch (EntityNotFoundException e){
            return BodyData.ERROR("데이터가 없습니다");
        }
    }
}
