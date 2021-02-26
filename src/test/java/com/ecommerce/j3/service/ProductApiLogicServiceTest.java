package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.ProductDto;
import com.ecommerce.j3.domain.entity.Product;
import com.ecommerce.j3.domain.mapper.ProductMapper;
import com.ecommerce.j3.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class ProductApiLogicServiceTest {
    @Autowired ProductRepository productRepository;
    @Autowired ProductMapper productMapper;

    @Test
    @Rollback(value = false)
    @Transactional
    void testInsertProduct(){
        Product product = productMapper.toEntity(ProductDto.ProductApiRequest.builder()
                .sellerId(2L)
                .price(400)
                .title("new")
                .metaTitle("meta")
                .slug("ppp3")
                .sku("")
                .discountPrice(0)
                .quantity(0)
                .build());
        productRepository.insert(product);
//        System.out.println("RESULT " +result);
//        assertThat(result).isNotNull();
    }
}