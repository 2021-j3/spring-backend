package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.ProductDto.ProductApiRequest;
import com.ecommerce.j3.controller.dto.ProductDto.ProductApiResponse;
import com.ecommerce.j3.controller.dto.ProductDto.SearchCondition;
import com.ecommerce.j3.domain.entity.Product;
import com.ecommerce.j3.domain.mapper.CommonMapper;
import com.ecommerce.j3.domain.mapper.ProductMapper;
import com.ecommerce.j3.repository.ProductRepository;
import com.ecommerce.j3.repository.ProductSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    private final CategoryApiLogicService categoryApiLogicService;
    private final TagApiLogicService tagApiLogicService;

    /**
     * 제품 추가
     * @param request { ProductApiRequest } 필수 필드가 모두 채워진 request
     * @return ProductApiResponse
     */
    public ProductApiResponse saveProduct(ProductApiRequest request) {
        Product product = productMapper.toEntity(request);
        productRepository.save(product);
        return productMapper.toApiResponse(product);
    }

    /**
     * 제품 갱신
     * @param request { ProductApiRequest } 필수 필드 + id 필드가 모두 채워진 request
     * @return ProductApiResponse
     */
    public ProductApiResponse updateProduct(ProductApiRequest request) {
        Product productFromDB = findById(request.getProductId());
        productMapper.updateFromDto(productFromDB, request);
        productRepository.save(productFromDB);
        return productMapper.toApiResponse(productFromDB);
    }

    /**
     * 제품 검색
     * @param productId { Long } 검색할 id
     * @return ProductApiResponse
     */
    public ProductApiResponse findProduct(Long productId) {
        Product productFromDB = findById(productId);
        return productMapper.toApiResponse(productFromDB);
    }

    /**
     * 제품 검색
     * @param searchCondition { SearchCondition } query, minPrice, maxPrice, cats, tags 모두 null 가능
     * @return List<ProductApiResponse>
     */
    public List<ProductApiResponse> searchProducts(SearchCondition searchCondition, Pageable pageable){
        Specification<Product> productSpecs = Specification.where(null);
        productSpecs = productSpecs
                .and(ProductSpecs.withKeywords(searchCondition.getQuery()))
                .and(ProductSpecs.fromPrice(searchCondition.getMinPrice()))
                .and(ProductSpecs.toPrice(searchCondition.getMaxPrice()))
                .and(ProductSpecs.fromCategories(categoryApiLogicService.findByIds(searchCondition.getCategoryIds())))
                .and(ProductSpecs.fromTags(tagApiLogicService.findByIds(searchCondition.getTagIds())));
        return productRepository.findAll(productSpecs, pageable).getContent()
                .stream().map(productMapper::toApiResponse).collect(Collectors.toList());
    }

    /**
     * 제품 삭제
     * @param id { Long }  삭제할 id
     */
    public void removeProduct(Long id) {
        productRepository.deleteById(id);
    }

    // 패키지 한정자, service 패키지 내에서만 접근 가능
    List<ProductApiResponse> findAllProduct() {
        return productRepository.findAll().stream().map(productMapper::toApiResponse).collect(Collectors.toList());
    }

    Product findById(Long productId){
        return productRepository.findById(productId).orElseThrow(EntityNotFoundException::new);
    }
}
