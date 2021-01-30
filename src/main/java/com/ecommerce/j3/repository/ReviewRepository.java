package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryInterface {
    @Override
    Optional<Review> findOneByParent(Review review);
}
