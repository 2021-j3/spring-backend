package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.Account;
import com.ecommerce.j3.domain.Product;
import com.ecommerce.j3.domain.Review;
import com.ecommerce.j3.repository.*;
import com.fasterxml.jackson.core.JsonProcessingException;
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
        account.setNickname("REVIEW_of_user");
        account.setPasswordHash("passHash");
        account.setEmail("example2@mail.com");
        account.setPhoneNumber("000-000-001");
        accountService.save(account);
        accountId = account.getAccountId();


        // given
        Account seller = new Account();
        seller.setNickname("REVIEW_of_seller");
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
        Review reviewByUser = new Review();
        reviewByUser.setProduct(productService.findOne(productId).get());
        reviewByUser.setAccount(accountService.findOne(accountId).get());
        reviewByUser.setRate((short) 0);
        reviewByUser.setTitle("title");
        reviewByUser.setContent("content");
        reviewService.save(reviewByUser);


        Review reviewBySeller = new Review();
        reviewBySeller.setProduct(productService.findOne(productId).get());
        reviewBySeller.setAccount(accountService.findOne(sellerId).get());
        reviewBySeller.setRate((short) 0);
        reviewBySeller.setTitle("title");
        reviewBySeller.setContent("content");
        // user가 쓴 리뷰를 parent로 갖음
        reviewBySeller.setParent(reviewByUser);
        reviewService.save(reviewBySeller);

        // user가 쓴 리뷰
        Review reviewFromDB = reviewService.findOne(reviewByUser.getReviewId()).get();
        // user가 쓴 리뷰를 parent로 갖는 리뷰
        Review reviewFromDB2 = reviewService.findOneByParent(reviewByUser).get();

        System.out.println("\n===============CUSTOM TEST OUTPUT START===============");

        System.out.println(reviewFromDB.getAccount().getNickname());

        System.out.println(reviewFromDB2.getAccount().getNickname());

        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(reviewFromDB));

            System.out.println(mapper.writeValueAsString(reviewFromDB2));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("===============CUSTOM TEST OUTPUT END===============\n");
    }
}