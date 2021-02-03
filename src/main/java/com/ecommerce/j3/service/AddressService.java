package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.entity.Address;
import com.ecommerce.j3.domain.mapper.AddressMapper;
import com.ecommerce.j3.controller.dto.AddressDto;
import com.ecommerce.j3.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressDto.AddressApiResponse save(AddressDto.AddressApiRequest request){
        Address address = addressMapper.toEntity(request);
        addressRepository.save(address);
        return addressMapper.toDto(address);
    }

    public Address update(Address address){
        addressRepository.save(address);
        return address;
    }

    public Optional<Address> findOne(Long addressId){
        return addressRepository.findById(addressId);
    }

    public List<Address> findAll(){
        return addressRepository.findAll();
    }

    public void remove(Address address){
        addressRepository.delete(address);
    }
}
