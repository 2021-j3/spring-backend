package com.ecommerce.j3.domain.repository;

import com.ecommerce.j3.J3ApplicationTests;
import com.ecommerce.j3.domain.entity.Review;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReviewRepositoryTest extends J3ApplicationTests {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Transactional
    @Test
    public void create(){

        Review review = new Review();

        review.setReviewId(2L);
       // review.setParentId(4L);
        review.setParent(reviewRepository.getOne(4L));
        review.setAccount(accountRepository.getOne(2L));
        review.setProduct(productRepository.getOne(2L));
        review.setRate(2F);
        review.setTitle("review title");

        review.setContent("review Content");

        Review netReview = reviewRepository.save(review);
        Assert.assertNotNull(netReview);
    }

//    @Test
//    public void read(){
//        Optional<Review> optionalReview = Optional.of(reviewRepository.getOne(4L));
//
//        optionalReview.ifPresent(r -> {
//            System.out.println(r.getRate());
//        });
//    }
}
