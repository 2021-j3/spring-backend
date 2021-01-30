package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.entity.Review;

import java.util.Optional;

public interface ReviewRepositoryInterface {
    Optional<Review> findOneByParent(Review review);
}
