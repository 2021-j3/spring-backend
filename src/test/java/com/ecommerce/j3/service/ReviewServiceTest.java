package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.Account;
import com.ecommerce.j3.domain.Product;
import com.ecommerce.j3.domain.Review;
import com.ecommerce.j3.repository.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
class ReviewServiceTest {
    @Autowired ReviewService reviewService;
    @Autowired ReviewRepository reviewRepository;
    @Autowired AccountService accountService;
    @Autowired AccountRepository accountRepository;
    @Autowired ProductService productService;
    @Autowired ProductRepository productRepository;


    Long accountId;
    Long productId;

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

        // given
        Product product = new Product();
        product.setTitle("title");
        product.setSlug("/dfasd");
        product.setSku("dfsa");
        product.setPrice(123.4f);
        product.setDiscount(13.3f);
        product.setQuantity((short)1);
        product.setAccount(accountService.findOne(accountId).get());
        // when
        productService.save(product);
        productId = product.getId();
    }

    @Test
    void save() {
        // given
        Review review = new Review();
        review.setProduct(productService.findOne(productId).get());
        review.setAccount(accountService.findOne(accountId).get());
        review.setRate((short) 0);
        review.setTitle("title");
        review.setPublishedAt((byte) 0);
        review.setContent("content");
        // when
        reviewService.save(review);

        //then
        Review reviewFromDB = reviewRepository.getOne(review.getId());
        Assertions
                .assertThat(review.getTitle())
                .isEqualTo(reviewFromDB.getTitle());
    }

    @Test
    void update() {
        // given
        Review review = new Review();
        review.setProduct(productService.findOne(productId).get());
        review.setAccount(accountService.findOne(accountId).get());
        review.setRate((short) 0);
        review.setTitle("title");
        review.setPublishedAt((byte) 0);
        review.setContent("content");
        reviewService.save(review);

        // when
        String new_title = "new title";
        review.setTitle(new_title);
        reviewService.update(review);

        // then
        Review reviewFromDB = reviewRepository.getOne(review.getId());
        Assertions
                .assertThat(new_title)
                .isEqualTo(reviewFromDB.getTitle());
    }

    @Test
    void findOneById() {
        // given
        Review review = new Review();
        review.setProduct(productService.findOne(productId).get());
        review.setAccount(accountService.findOne(accountId).get());
        review.setRate((short) 0);
        review.setTitle("title");
        review.setPublishedAt((byte) 0);
        review.setContent("content");
        reviewService.save(review);

        Review reviewFromDB = reviewService.findOne(review.getId()).get();
        Assertions
                .assertThat(review.getTitle())
                .isEqualTo(reviewFromDB.getTitle());
    }


    @Test
    void remove() {
        // given
        Review review = new Review();
        review.setProduct(productService.findOne(productId).get());
        review.setAccount(accountService.findOne(accountId).get());
        review.setRate((short) 0);
        review.setTitle("title");
        review.setPublishedAt((byte) 0);
        review.setContent("content");
        reviewService.save(review);
        // when
        int cnt_that = reviewService.findAll().size();
        reviewService.remove(review);

        //then
        int cnt_now = reviewService.findAll().size();
        Assertions
                .assertThat(cnt_that - 1)
                .isEqualTo(cnt_now);
    }
}