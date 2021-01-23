package com.ecommerce.j3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.j3.domain.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findOneByParent(Review review);
}
