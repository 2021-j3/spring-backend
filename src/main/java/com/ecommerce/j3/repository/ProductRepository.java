package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
