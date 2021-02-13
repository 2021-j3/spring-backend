package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.ReviewDto;
import com.ecommerce.j3.domain.entity.Review;
import com.ecommerce.j3.domain.mapper.ReviewMapper;
import com.ecommerce.j3.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewApiLogicService {
    private ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewDto.ReviewApiResponse saveReview(ReviewDto.ReviewApiRequest request) {
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
        return reviewMapper.toApiResponse(review);
    }

    public ReviewDto.ReviewApiResponse updateReview(ReviewDto.ReviewApiRequest request) {
        Review reviewFromDB = findById(request.getReviewId());
        reviewMapper.updateFromDto(reviewFromDB, request);
        reviewRepository.save(reviewFromDB);
        return reviewMapper.toApiResponse(reviewFromDB);
    }

    public ReviewDto.ReviewApiResponse findReview(Long reviewId) {
        Review reviewFromDB = findById(reviewId);
        return reviewMapper.toApiResponse(reviewFromDB);
    }

    public void removeReview(Long id) {
        reviewRepository.deleteById(id);
    }

    // 패키지 한정자, service패키지 내에서만 접근 가능
    Review findById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Optional<Review> findOneByParent(Review review) {
        return reviewRepository.findOneByParent(review);
    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public void remove(Review review) {
        reviewRepository.delete(review);
    }
}
