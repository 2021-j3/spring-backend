package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.ReviewDto;
import com.ecommerce.j3.domain.entity.Review;
import com.ecommerce.j3.domain.mapper.ReviewMapper;
import com.ecommerce.j3.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewService {
    private ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewDto.ReviewApiResponse save(ReviewDto.ReviewApiRequest request){
        Long id = reviewRepository.save(
                request.getReviewId(),
                request.getParentId(),
                request.getOrderItemId(),
                request.getAccountId(),
                request.getRate(),
                request.getTitle(),
                request.getContent(),
                request.getCreatedAt());
        Review review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("실패!"));
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
