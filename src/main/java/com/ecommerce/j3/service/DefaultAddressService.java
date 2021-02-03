package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.AccountDto;
import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.Address;
import com.ecommerce.j3.domain.mapper.AddressMapper;
import com.ecommerce.j3.controller.dto.AddressDto;
import com.ecommerce.j3.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultAddressService {
    private final AccountRepository accountRepository;
    private final AddressMapper addressMapper;

    public void setDefaultAddress(AddressDto.AddressApiRequest request){
        Address address = addressMapper.toEntity(request);
        Long ac = request.getAccountId();
        long ad = request.getAddressId();

        AccountDto.AccountApiRequest req = AccountDto.AccountApiRequest.builder()

                .build();
    }
}
