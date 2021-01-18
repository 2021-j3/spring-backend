package com.ecommerce.j3.domain.repository;

import com.ecommerce.j3.J3ApplicationTests;
import com.ecommerce.j3.domain.entity.Address;
import com.ecommerce.j3.domain.entity.Review;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.net.InetAddress;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressRepositoryTest extends J3ApplicationTests {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Transactional
    @Test
    public void create(){

        Address address = new Address();

        address.setAddress("address");
        address.setRoadAddress("Road Address");
        address.setCity("City");
        address.setProvince("Province");
        address.setCountry("Country");
        address.setZipCode(11111);

        Address newAddress = addressRepository.save(address);
        Assert.assertNotNull(newAddress);
    }
}