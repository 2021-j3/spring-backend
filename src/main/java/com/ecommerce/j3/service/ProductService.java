package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.Product;
import com.ecommerce.j3.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productsRepository;

    @Autowired
    public ProductService(ProductRepository productsRepository){this.productsRepository = productsRepository;}

    public Product save(Product products){
        productsRepository.save(products);
        return products;
    }

    public Product update(Product products){
        productsRepository.save(products);
        return products;
    }

    public Optional<Product> findOne(Long productId){
        return productsRepository.findById(productId);
    }

    public List<Product> findAll(){
        return productsRepository.findAll();
    }

    public void remove(Product products){
        productsRepository.delete(products);
    }
}
