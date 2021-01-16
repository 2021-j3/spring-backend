package com.ecommerce.j3.domain.repository;

import com.ecommerce.j3.J3ApplicationTests;
import com.ecommerce.j3.domain.entity.Order;

import com.ecommerce.j3.domain.repository.AccountRepository;
import com.ecommerce.j3.domain.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderRepositoryTest extends J3ApplicationTests {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    @Test
    public void create(){
        Order order = new Order();

        order.setAccount(accountRepository.getOne(2L));
        order.setSessionId("session");
        order.setToken("Token");
        order.setStatus(2);
        order.setItemPriceTotal(BigDecimal.valueOf(2222));
        order.setItemDiscount(22);
        order.setTax(3.3F);
        order.setShipping(BigDecimal.valueOf(2500));
        order.setUserDiscount(20F);
        order.setGrandTotal(200F);
        order.setFirstName("FirstName");
        order.setLastName("LastName");
        order.setEmail("Email");
        order.setPhoneNumber("010-2222-2222");
        order.setRoadAddress("RoadAddress");
        order.setAddress("Address");
        order.setCity("City");
        order.setProvince("Province");
        order.setCountry("Country");
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        order.setContent("Content");

        Order newOrder = orderRepository.save(order);
        Assert.assertNotNull(newOrder);
    }
}
