package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.entity.Address;
import com.ecommerce.j3.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    private AddressRepository addresssRepository;

    @Autowired
    public AddressService(AddressRepository addresssRepository){this.addresssRepository = addresssRepository;}

    public Address save(Address addresss){
        addresssRepository.save(addresss);
        return addresss;
    }

    public Address update(Address addresss){
        addresssRepository.save(addresss);
        return addresss;
    }

    public Optional<Address> findOne(Long addressId){
        return addresssRepository.findById(addressId);
    }

    public List<Address> findAll(){
        return addresssRepository.findAll();
    }

    public void remove(Address addresss){
        addresssRepository.delete(addresss);
    }
}
