package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.Account;
import com.ecommerce.j3.domain.Watch;
import com.ecommerce.j3.domain.Product;
import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.repository.WatchRepository;
import com.ecommerce.j3.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Transactional
@SpringBootTest
class WatchServiceTest {
    @Autowired AccountService accountService;
    @Autowired AccountRepository accountRepository;
    @Autowired WatchService watchService;
    @Autowired WatchRepository watchRepository;
    @Autowired ProductService productService;
    @Autowired ProductRepository productRepository;

    Long accountId;
    Long productId;

    @BeforeEach
    void beforeEach(){
        // given
        Account account = new Account();
        account.setNickname("CART ITEM TEST");
        account.setPasswordHash("passHash");
        account.setEmail("example1@mail.com");
        account.setPhoneNumber("000-000-000");
        accountService.save(account);
        accountId = account.getAccountId();

        // given
        Product product = new Product();
        product.setTitle("title");
        product.setSlug("/dfasd");
        product.setSku("dfsa");
        product.setPrice(BigDecimal.valueOf(123.4f));
        product.setDiscountRate(13.3f);
        product.setQuantity((short)1);
        product.setAccount(accountService.findOne(accountId).get());
        // when
        productService.save(product);
        productId = product.getProductId();
    }

    @Test
    void save(){
        // given
        Watch watch = new Watch();
        watch.setAccount(accountService.findOne(accountId).get());
        watch.setProduct(productService.findOne(productId).get());
        // when
        watchService.save(watch);

        //then
        Watch watchFromDB = watchRepository.getOne(watch.getWatchId());
        Assertions
                .assertThat(watch.getAccount())
                .isEqualTo(watchFromDB.getAccount());
    }

    @Test
    void update() {
        // given
        Watch watch = new Watch();
        watch.setAccount(accountService.findOne(accountId).get());
        watch.setProduct(productService.findOne(productId).get());
        watchService.save(watch);
        // when
        watchService.increase(watch);

        //then
        Watch watchFromDB = watchRepository.getOne(watch.getWatchId());
        Assertions
                .assertThat(watch.getWatchCount())
                .isEqualTo(watchFromDB.getWatchCount());
    }

    @Test
    void findOneById() {
        // given
        Watch watch = new Watch();
        watch.setAccount(accountService.findOne(accountId).get());
        watch.setProduct(productService.findOne(productId).get());
        watchService.save(watch);

        //then
        Watch watchFromDB = watchRepository.getOne(watch.getWatchId());
        Assertions
                .assertThat(watch.getAccount())
                .isEqualTo(watchFromDB.getAccount());
    }

    @Test
    void remove() {
        // given
        Watch watch = new Watch();
        watch.setAccount(accountService.findOne(accountId).get());
        watch.setProduct(productService.findOne(productId).get());
        watchService.save(watch);
        // when
        List<Watch> watchList = watchService.findAll();
        int cnt_that = watchList.size();
        watchService.remove(watch);

        //then
        int cnt_now = watchService.findAll().size();
        Assertions
                .assertThat(cnt_that - 1)
                .isEqualTo(cnt_now);
    }
}