package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.Address;
import com.ecommerce.j3.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;


    public Address save(Address address){
        addressRepository.save(address);
        return address;
    }




}
