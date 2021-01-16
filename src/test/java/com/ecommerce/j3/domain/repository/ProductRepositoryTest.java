package com.ecommerce.j3.domain.repository;

import com.ecommerce.j3.J3ApplicationTests;

import com.ecommerce.j3.domain.entity.Product;
import com.ecommerce.j3.domain.repository.AccountRepository;
import com.ecommerce.j3.domain.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductRepositoryTest extends J3ApplicationTests {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    @Test
    public void create(){
        Product product = new Product();

        product.setAccount(accountRepository.getOne(2L));
        product.setTitle("ProductTest01");
        product.setMetaTitle("What?");
        product.setSlug("slug");
        product.setSummary("Summary");
        product.setSku("SKU");
        product.setPrice(BigDecimal.valueOf(2222));
        product.setDiscount(2F);
        product.setQuantity(2);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        product.setPublishedAt(LocalDateTime.now());
        product.setStartsAt(LocalDateTime.now());
        product.setEndsAt(LocalDateTime.now());
        product.setContent("product");

        Product newProduct = productRepository.save(product);

        Assert.assertNotNull(newProduct);
    }

    @Test
    public void read(){
        productRepository.findById(2L);
    }
}