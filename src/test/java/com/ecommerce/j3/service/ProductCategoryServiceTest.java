package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.Account;
import com.ecommerce.j3.domain.Category;
import com.ecommerce.j3.domain.Product;
import com.ecommerce.j3.domain.ProductCategory;
import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.repository.CategoryRepository;
import com.ecommerce.j3.repository.ProductCategoryRepository;
import com.ecommerce.j3.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.utility.RandomString;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Transactional
@SpringBootTest
class ProductCategoryServiceTest {
    @Autowired AccountService accountService;
    @Autowired AccountRepository accountRepository;
    @Autowired ProductService productService;
    @Autowired ProductRepository productRepository;
    @Autowired CategoryService categoryService;
    @Autowired CategoryRepository categoryRepository;
    @Autowired ProductCategoryService productCategoryService;
    @Autowired ProductCategoryRepository productCategoryRepository;

     Long accountId;
     Long productId;
     Long categoryId;

    @BeforeEach
    void beforeEach(){
        RandomString randomString = new RandomString();
        // given
        Account account = new Account();
        account.setNickname("PRODUCT");
        account.setPasswordHash(randomString.nextString());
        account.setEmail(randomString.nextString());
        account.setPhoneNumber(randomString.nextString());
        accountService.save(account);
        accountId = account.getAccountId();

        // given
        Product product = new Product();
        product.setTitle("title");
        product.setSlug("/dfasd");
        product.setSku("dfsa");
        product.setPrice(BigDecimal.valueOf(123.4f));
        product.setDiscountRate(13.3f);
        product.setQuantity((short)1);
        product.setAccount(accountService.findOne(accountId).get());
        productService.save(product);

        productId = product.getProductId();


        Category category4 = new Category();
        category4.setTitle("갤탭");
        category4.setSlug("fasdf");
        category4.setContent("content");
        categoryService.save(category4);

        categoryId = category4.getCategoryId();
    }

    @Test
    void save(){
        Product p = productService.findOne(productId).get();
        Category c = categoryService.findOne(categoryId).get();

        ProductCategory pc = new ProductCategory();

        pc.setProduct(p);
        pc.setCategory(c);

        productCategoryService.save(pc);

        ProductCategory pcFromDB1 = productCategoryService.findByProduct(p).get(0);
        Assertions.assertThat(pc).isEqualTo(pcFromDB1);
    }

    @Test
    void update() {
    }

    @Test
    void findOneById() {
    }

    @Test
    void remove() {
    }
}