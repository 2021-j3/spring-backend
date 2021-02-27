package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.AddressDto.AddressApiRequest;
import com.ecommerce.j3.controller.dto.AddressDto.AddressApiResponse;
import com.ecommerce.j3.domain.entity.Address;
import com.ecommerce.j3.domain.mapper.AddressMapper;
import com.ecommerce.j3.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@Transactional(readOnly = true)
@Transactional
@RequiredArgsConstructor
public class AddressApiLogicService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressApiResponse saveAddress(AddressApiRequest request) {
        Address address = addressMapper.toEntity(request);
        addressRepository.save(address);
        return addressMapper.toApiResponse(address);
    }

    public AddressApiResponse updateAddress(AddressApiRequest request) {
        Address addressFromDB = findById(request.getAddressId());
        addressMapper.updateFromDto(addressFromDB, request);
        addressRepository.save(addressFromDB);
        return addressMapper.toApiResponse(addressFromDB);
    }

    public AddressApiResponse findAddress(Long addressId) {
        Address addressFromDB = findById(addressId);
        return addressMapper.toApiResponse(addressFromDB);
    }

    public List<AddressApiResponse> findAddressesByAccountId(Long accountId){
        return addressRepository.findByAccountId(accountId)
                .stream().map(addressMapper::toApiResponse)
                .collect(Collectors.toList());
    }

    public void removeAddress(Long id) {
        addressRepository.deleteById(id);
    }

    // 패키지 한정자, service패키지 내에서만 접근 가능
    Address findById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
