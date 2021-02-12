package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.ProductDto.ProductApiRequest;
import com.ecommerce.j3.controller.dto.ProductDto.ProductApiResponse;
import com.ecommerce.j3.domain.entity.Product;
import com.ecommerce.j3.domain.mapper.ProductMapper;
import com.ecommerce.j3.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
//@Transactional(readOnly = true)
@Transactional
@RequiredArgsConstructor
public class ProductApiLogicService implements ServiceCrudInterface<ProductApiRequest, ProductApiResponse>{
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductApiResponse save(ProductApiRequest request){
        Product product = productMapper.toEntity(request);
        productRepository.save(product);
        return productMapper.toApiResponseDto(product);
    }

    @Override
    public ProductApiResponse update(ProductApiRequest request) {
        Product productFromDB = productRepository.findById(request.getProductId())
                .orElseThrow(EntityNotFoundException::new);
        productMapper.updateFromDto(productFromDB, request);
        productRepository.save(productFromDB);
        return productMapper.toApiResponseDto(productFromDB);
    }

    @Override
    public ProductApiResponse findOne(Long productId){
        Product productFromDB = productRepository.findById(productId)
                .orElseThrow(EntityNotFoundException::new);
        return productMapper.toApiResponseDto(productFromDB);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }
    //////////////////////////////////////////////////////////////////////////

    public List<Product> findProducts(){
        return productRepository.findAll();
    }


/*
    public void remove(Product products){
        productsRepository.delete(products);
    }*/
}
