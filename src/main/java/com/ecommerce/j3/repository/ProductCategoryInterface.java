package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.Category;
import com.ecommerce.j3.domain.Product;
import com.ecommerce.j3.domain.ProductCategory;

import java.util.List;

public interface ProductCategoryInterface {
    List<ProductCategory> findByProduct(Product product);
    List<ProductCategory> findByCategory(Category category);
}