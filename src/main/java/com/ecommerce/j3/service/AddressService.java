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

@Service
//@Transactional(readOnly = true)
@Transactional
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

        public AddressApiResponse save(AddressApiRequest request){
        Address address = addressMapper.toEntity(request);
        addressRepository.save(address);
        return addressMapper.toDto(address);
    }

        public AddressApiResponse update(AddressApiRequest request) {
        Address addressFromDB = addressRepository.findById(request.getAddressId())
                .orElseThrow(EntityNotFoundException::new);
        addressMapper.updateFromDto(addressFromDB, request);
        addressRepository.save(addressFromDB);
        return addressMapper.toDto(addressFromDB);
    }

        public AddressApiResponse findOne(Long addressId){
        Address addressFromDB = addressRepository.findById(addressId)
                .orElseThrow(EntityNotFoundException::new);
        return addressMapper.toDto(addressFromDB);
    }

        public void remove(Long id) {
        addressRepository.deleteById(id);
    }
}
