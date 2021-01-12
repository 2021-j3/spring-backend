package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.Account;
import com.ecommerce.j3.domain.Product;
import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Transactional
@SpringBootTest
class ProductServiceTest {
    @Autowired AccountService accountService;
    @Autowired AccountRepository accountRepository;
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;

     Long accountId;

    @BeforeEach
    void beforeEach(){
        // given
        Account account = new Account();
        account.setNickname("nick");
        account.setPasswordHash("passHash");
        account.setEmail("example1@mail.com");
        account.setPhoneNumber("000-000-000");
        accountService.save(account);
        accountId = account.getId();
    }

    @Test
    void save(){
        // given
        Product product = new Product();
        product.setTitle("title");
        product.setSlug("/dfasd");
        product.setSku("dfsa");
        product.setPrice(123.4f);
        product.setDiscount(13.3f);
        product.setQuantity((short)1);
        product.setAccount(accountService.findOneById(accountId).get());
        // when
        productService.save(product);

        //then
        Product productFromDB = productRepository.getOne(product.getId());
        Assertions
                .assertThat(product.getTitle())
                .isEqualTo(productFromDB.getTitle());
    }

    @Test
    void update() {
        // given
        Product product = new Product();
        product.setTitle("title");
        product.setSlug("/dfasd");
        product.setSku("dfsa");
        product.setPrice(123.4f);
        product.setDiscount(13.3f);
        product.setQuantity((short)1);
        product.setAccount(accountService.findOneById(accountId).get());
        productService.save(product);
        // when
        product.setTitle("new_title");
        productService.update(product);

        //then
        Product productFromDB = productRepository.getOne(product.getId());
        Assertions
                .assertThat(product.getTitle())
                .isEqualTo(productFromDB.getTitle());
    }

    @Test
    void findOneById() {
        // given
        Product product = new Product();
        product.setTitle("title");
        product.setSlug("/dfasd");
        product.setSku("dfsa");
        product.setPrice(123.4f);
        product.setDiscount(13.3f);
        product.setQuantity((short)1);
        product.setAccount(accountService.findOneById(accountId).get());
        productService.save(product);

        //then
        Product productFromDB = productRepository.getOne(product.getId());
        Assertions
                .assertThat(product.getTitle())
                .isEqualTo(productFromDB.getTitle());
    }

    @Test
    void remove() {
        // given
        Product product = new Product();
        product.setTitle("title");
        product.setSlug("/dfasd");
        product.setSku("dfsa");
        product.setPrice(123.4f);
        product.setDiscount(13.3f);
        product.setQuantity((short)1);
        product.setAccount(accountService.findOneById(accountId).get());
        productService.save(product);
        // when
        int cnt_that = productService.findAll().size();
        productService.remove(product);

        //then
        int cnt_now = productService.findAll().size();
        Assertions
                .assertThat(cnt_that - 1)
                .isEqualTo(cnt_now);
    }
}