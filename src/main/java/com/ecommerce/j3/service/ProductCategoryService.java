package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.Category;
import com.ecommerce.j3.domain.Product;
import com.ecommerce.j3.domain.ProductCategory;
import com.ecommerce.j3.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService {
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    public ProductCategoryService(ProductCategoryRepository productCategoryRepository){this.productCategoryRepository = productCategoryRepository;}

    ProductCategory save(ProductCategory productCategory){
        productCategoryRepository.save(productCategory);
        return productCategory;
    }
    List<ProductCategory> findByProduct(Product product){ return productCategoryRepository.findByProduct(product);}
    List<ProductCategory> findByProduct(Category category){return productCategoryRepository.findByCategory(category);}
    void remove(ProductCategory productCategory){
        productCategoryRepository.delete(productCategory);
    }
}
