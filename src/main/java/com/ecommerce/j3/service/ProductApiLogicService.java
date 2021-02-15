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
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductApiLogicService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductApiResponse saveProduct(ProductApiRequest request) {
        Product product = productMapper.toEntity(request);
        productRepository.save(product);
        return productMapper.toApiResponse(product);
    }

    public ProductApiResponse updateProduct(ProductApiRequest request) {
        Product productFromDB = findById(request.getProductId());
        productMapper.updateFromDto(productFromDB, request);
        productRepository.save(productFromDB);
        return productMapper.toApiResponse(productFromDB);
    }

    public ProductApiResponse findProduct(Long productId) {
        Product productFromDB = findById(productId);
        return productMapper.toApiResponse(productFromDB);
    }

    public void removeProduct(Long id) {
        productRepository.deleteById(id);
    }

    // 패키지 한정자, service패키지 내에서만 접근 가능
    Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<ProductApiResponse> findAllProduct() {
        return productRepository.findAll().stream().map(productMapper::toApiResponse).collect(Collectors.toList());
    }
}
