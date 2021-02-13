package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.AddressDto;
import com.ecommerce.j3.controller.dto.AccountDto.AccountApiRequest;
import com.ecommerce.j3.domain.entity.Address;
import com.ecommerce.j3.domain.mapper.AddressMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultAddressApiService {
    private final AccountApiLogicService accountApiLogicService;
    private final AddressMapper addressMapper;

    public void setDefaultAddress(AddressDto.AddressApiRequest request) {
        Address address = addressMapper.toEntity(request);
        AccountApiRequest accountApiRequest = AccountApiRequest.builder()
                .accountId(address.getAccount().getAccountId())
                .defaultAddress(address)
                .build();
        accountApiLogicService.updateAccount(accountApiRequest);
    }
}
