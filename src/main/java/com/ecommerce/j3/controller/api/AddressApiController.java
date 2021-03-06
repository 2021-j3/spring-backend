package com.ecommerce.j3.controller.api;


import com.ecommerce.j3.controller.dto.BodyData;
import com.ecommerce.j3.controller.dto.AddressDto.AddressApiRequest;
import com.ecommerce.j3.controller.dto.AddressDto.AddressApiResponse;
import com.ecommerce.j3.domain.J3UserDetails;
import com.ecommerce.j3.service.AddressApiLogicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Api(tags = {"02. Address"})
@RestController
@RequestMapping("/api/address")
@AllArgsConstructor
public class AddressApiController{
    private final AddressApiLogicService addressService;

    @ApiOperation(value = "주소 추가", notes="주소를 추가한다")
    @PostMapping("")
    public BodyData<AddressApiResponse> create(@RequestBody AddressApiRequest request, Authentication authentication) {
        J3UserDetails userDetails = (J3UserDetails)authentication.getPrincipal();
        Long accountId = userDetails.getAccountId();
        request.setAccountId(accountId);
        addressService.saveAddress(request);
        addressService.updateDefaultAddress(request.getAccountId());
        return null;
    }

    @ApiOperation(value = "주소 일기", notes = "주소를 가져온다")
    @GetMapping("/{id}")
    public BodyData<AddressApiResponse> read(@PathVariable("id") Long id) {
        try {
            return BodyData.OK(addressService.findAddress(id));
        }catch (EntityNotFoundException e){
            return BodyData.ERROR("데이터가 없습니다");
        }
    }

    @ApiOperation(value = "주소 갱신", notes = "주소를 갱신한다.")
    @PutMapping("/{id}")
    public BodyData<AddressApiResponse> update(@PathVariable Long id, @RequestBody AddressApiRequest request) {
        try{
            return BodyData.OK(addressService.updateAddress(request));
        }catch (EntityNotFoundException e){
            return BodyData.ERROR("데이터가 없습니다");
        }
    }

    @ApiOperation(value = "주소 삭제", notes = "주소를 삭제한다.")
    @DeleteMapping("{id}")
    public BodyData delete(@PathVariable("id") Long id) {
        try{
            addressService.removeAddress(id);
            return BodyData.OK();
        }catch (EntityNotFoundException e){
            return BodyData.ERROR("데이터가 없습니다");
        }
    }

    @ApiOperation(value = "나의 주소", notes = "주소를 가져온다")
    @GetMapping("/my")
    public ResponseEntity<List<AddressApiResponse>> readMyAddresses(Authentication authentication){
        // 로그인한 유저를 가져옴
        J3UserDetails userDetails = (J3UserDetails)authentication.getPrincipal();
        Long accountId = userDetails.getAccountId();
        return ResponseEntity.ok(addressService.findMyAddresses(accountId));
    }
}
