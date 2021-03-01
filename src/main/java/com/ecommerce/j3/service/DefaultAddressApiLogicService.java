package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.AccountDto;
import com.ecommerce.j3.controller.dto.AddressDto.AddressApiResponse;
import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.Address;
import com.ecommerce.j3.domain.mapper.AccountMapper;
import com.ecommerce.j3.domain.mapper.AddressMapper;
import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor  // final 변수만 처리.
public class DefaultAddressApiLogicService {
    private final AccountRepository accountRepository;
    private final AddressRepository addressRepository;
    private final AccountMapper accountMapper;
    private final AddressMapper addressMapper;

    public AddressApiResponse findDefaultAddressOfAccount(Long accountId){
        return addressMapper.toApiResponse(findByAccountId(accountId));
    }

    public void saveMyDefaultAddress(Long accountId, Long addressId){
        Address address = addressRepository.findById(addressId).orElseThrow(EntityNotFoundException::new);
        Account accountFromDB = accountRepository.findById(accountId).orElseThrow(EntityNotFoundException::new);
        AccountDto.AccountApiRequest request = AccountDto.AccountApiRequest.builder()
                .accountId(accountId)
                .defaultAddress(address)
                .build();
        Account updateAccount = accountMapper.updateFromDto(accountFromDB, request);
        accountRepository.saveAndFlush(updateAccount);
    }

    Address findByAccountId(Long accountId){
        Account accountFromDB = accountRepository.findById(accountId).orElseThrow(EntityNotFoundException::new);
        return accountFromDB.getDefaultAddress();
    }
}
