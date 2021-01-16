package com.ecommerce.j3.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.j3.domain.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
