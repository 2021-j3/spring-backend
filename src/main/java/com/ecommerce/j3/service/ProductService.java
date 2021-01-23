package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.entity.Product;
import com.ecommerce.j3.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.ReadOnlyBufferException;
import java.util.List;
import java.util.Optional;

@Service
//@Transactional(readOnly = true)
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productsRepository;


    public void saveProduct(Product products){
        productsRepository.save(products);
    }

    public Product findOne(Long productId){
        return productsRepository.findOne(productId);
    }
    public List<Product> findProducts(){
        return productsRepository.findAll();
    }


/*
    public void remove(Product products){
        productsRepository.delete(products);
    }*/
}
