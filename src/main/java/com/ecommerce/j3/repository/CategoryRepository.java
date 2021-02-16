package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByCategoryIdIn(List<Long> categoryIds);
    Set<Category> getByCategoryIdIn(Set<Long> categoryIds);
}
