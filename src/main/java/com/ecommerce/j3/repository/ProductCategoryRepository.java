package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.Category;
import com.ecommerce.j3.domain.Product;
import com.ecommerce.j3.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long>, ProductCategoryInterface{
    @Override
    List<ProductCategory> findByProduct(Product product);

    @Override
    List<ProductCategory> findByCategory(Category category);
}
