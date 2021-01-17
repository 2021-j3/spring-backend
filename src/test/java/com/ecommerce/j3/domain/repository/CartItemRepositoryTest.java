package com.ecommerce.j3.domain.repository;

import com.ecommerce.j3.J3ApplicationTests;
import com.ecommerce.j3.domain.entity.CartItem;
import com.ecommerce.j3.domain.repository.AccountRepository;
import com.ecommerce.j3.domain.repository.CartItemRepository;
import com.ecommerce.j3.domain.repository.ProductRepository;
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
public class CartItemRepositoryTest extends J3ApplicationTests {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Transactional
    @Test
    public void create(){
        CartItem cartItem = new CartItem();
        cartItem.setAccount(accountRepository.getOne(2L));
        cartItem.setProduct(productRepository.getOne(2L));
        cartItem.setSku("CartItem");
        cartItem.setPrice(BigDecimal.valueOf(2222));
        cartItem.setDiscountRate(22F);
        cartItem.setQuantity(2);
//        cartItem.setActive(2);
        cartItem.setCreatedAt(LocalDateTime.now());
        cartItem.setUpdatedAt(LocalDateTime.now());
        cartItem.setContent("test");

        CartItem newAccount = cartItemRepository.save(cartItem);
        Assert.assertNotNull(newAccount);
    }
}
