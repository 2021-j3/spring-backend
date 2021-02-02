package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.ReviewDto;
import com.ecommerce.j3.domain.entity.Review;
import com.ecommerce.j3.domain.entity.Product;
import com.ecommerce.j3.domain.entity.Review;
import com.ecommerce.j3.domain.mapper.ReviewMapper;
import com.ecommerce.j3.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewService {
    private ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewDto.ReviewApiResponse save(ReviewDto.ReviewApiRequest request){
        Review review = reviewMapper.toEntity(request);
        reviewRepository.save(review);
        return reviewMapper.toDto(review);
    }
    public Review save(Review review){
        reviewRepository.save(review);
        return review;
    }

    public Review update(Review review){
        reviewRepository.save(review);
        return review;
    }

    public Optional<Review> findOne(Long reviewId){
        return reviewRepository.findById(reviewId);
    }
    public Optional<Review> findOneByParent(Review review) { return  reviewRepository.findOneByParent(review);}

    public List<Review> findAll(){
        return reviewRepository.findAll();
    }

    public void remove(Review review){
        reviewRepository.delete(review);
    }
}
