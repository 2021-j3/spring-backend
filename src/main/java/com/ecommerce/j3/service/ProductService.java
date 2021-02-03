package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.ProductDto;
import com.ecommerce.j3.domain.entity.Product;
import com.ecommerce.j3.domain.mapper.ProductMapper;
import com.ecommerce.j3.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
//@Transactional(readOnly = true)
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductDto.ProductApiResponse save(ProductDto.ProductApiRequest request){
        Product product = productMapper.toEntity(request);
        productRepository.save(product);
        return productMapper.toDto(product);
    }

    public void saveProduct(Product products){
        productRepository.save(products);
    }

    public Product findOne(Long productId){
        return productRepository.findById(productId).orElseThrow();
    }
    public List<Product> findProducts(){
        return productRepository.findAll();
    }


/*
    public void remove(Product products){
        productsRepository.delete(products);
    }*/
}
