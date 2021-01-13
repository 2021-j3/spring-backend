package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.Account;
import com.ecommerce.j3.domain.Product;
import com.ecommerce.j3.domain.Review;
import com.ecommerce.j3.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import java.math.BigDecimal;


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
    Long sellerId;
    Long productId;

    @BeforeEach
    void beforeEach(){
        // given
        Account account = new Account();
        account.setNickname("REVIEW");
        account.setPasswordHash("passHash");
        account.setEmail("example2@mail.com");
        account.setPhoneNumber("000-000-001");
        accountService.save(account);
        accountId = account.getAccountId();


        // given
        Account seller = new Account();
        seller.setNickname("seller");
        seller.setPasswordHash("passHash");
        seller.setEmail("example3@mail.com");
        seller.setPhoneNumber("000-000-002");
        accountService.save(seller);
        sellerId = seller.getAccountId();


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
        Review reviewFromDB = reviewRepository.getOne(review.getReviewId());
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
        Review reviewFromDB = reviewRepository.getOne(review.getReviewId());
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

        Review reviewFromDB = reviewService.findOne(review.getReviewId()).get();
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

    @Test
    void Parent(){
        Review review1 = new Review();
        review1.setProduct(productService.findOne(productId).get());
        review1.setAccount(accountService.findOne(accountId).get());
        review1.setRate((short) 0);
        review1.setTitle("title");
        review1.setPublishedAt((byte) 0);
        review1.setContent("content");
        reviewService.save(review1);


        Review review2 = new Review();
        review2.setProduct(productService.findOne(productId).get());
        review2.setAccount(accountService.findOne(sellerId).get());
        review2.setRate((short) 0);
        review2.setTitle("title");
        review2.setPublishedAt((byte) 0);
        review2.setContent("content");
        review2.setParent(review2);
        reviewService.save(review2);

        ObjectMapper mapper = new ObjectMapper();
        Review reviewFromDB = reviewService.findOne(review2.getReviewId()).get();

        Review reviewFromDB2 = reviewService.findOneByParent(reviewFromDB).get();

        System.out.println(reviewFromDB.getAccount().getNickname());

        System.out.println(reviewFromDB2.getAccount().getNickname());
    }
}