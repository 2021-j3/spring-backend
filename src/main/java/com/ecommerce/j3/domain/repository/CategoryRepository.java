package com.ecommerce.j3.domain.repository;

import com.ecommerce.j3.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long> {
}
