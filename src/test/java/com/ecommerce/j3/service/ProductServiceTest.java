package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.Account;
import com.ecommerce.j3.domain.Category;
import com.ecommerce.j3.domain.Product;
import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.repository.CategoryRepository;
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
class ProductServiceTest {
    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;

    Long accountId;
    Category catApple;
    Category catTablet;

    @BeforeEach
    void beforeEach() {
        RandomString randomString = new RandomString();
        // given
        Account account = new Account();
        account.setNickname("PRODUCT");
        account.setPasswordHash(randomString.nextString());
        account.setEmail(randomString.nextString());
        account.setPhoneNumber(randomString.nextString());
        accountService.save(account);
        accountId = account.getAccountId();

        Category catApple = new Category();
        catApple.setTitle("애플");
        catApple.setSlug("fasdf");
        catApple.setContent("content");
        categoryService.save(catApple);
        this.catApple = catApple;

        // given
        Category catTablet = new Category();
        catTablet.setTitle("태블릿");
        catTablet.setSlug("fasdf");
        catTablet.setContent("content");
        categoryService.save(catTablet);
        this.catTablet = catTablet;
    }

    @Test
    void save() {
        // given
        Product product = new Product();
        product.setTitle("title");
        product.setSlug("/dfasd");
        product.setSku("dfsa");
        product.setPrice(BigDecimal.valueOf(123.4f));
        product.setDiscountRate(13.3f);
        product.setQuantity((short) 1);
        product.setAccount(accountService.findOne(accountId).get());
        // when
        productService.save(product);

        //then
        Product productFromDB = productRepository.getOne(product.getProductId());
        Assertions
                .assertThat(product.getTitle())
                .isEqualTo(productFromDB.getTitle());
    }

    @Test
    void update() {
        // given
        Product product = new Product();
        product.setTitle("title");
        product.setSlug("/dfasd");
        product.setSku("dfsa");
        product.setPrice(BigDecimal.valueOf(123.4f));
        product.setDiscountRate(13.3f);
        product.setQuantity((short) 1);
        product.setAccount(accountService.findOne(accountId).get());
        productService.save(product);
        // when
        product.setTitle("new_title");
        productService.update(product);

        //then
        Product productFromDB = productRepository.getOne(product.getProductId());
        Assertions
                .assertThat(product.getTitle())
                .isEqualTo(productFromDB.getTitle());
    }

    @Test
    void findOneById() {
        // given
        Product product = new Product();
        product.setTitle("title");
        product.setSlug("/dfasd");
        product.setSku("dfsa");
        product.setPrice(BigDecimal.valueOf(123.4f));
        product.setDiscountRate(13.3f);
        product.setQuantity((short) 1);
        product.setAccount(accountService.findOne(accountId).get());
        productService.save(product);

        //then
        Product productFromDB = productRepository.getOne(product.getProductId());
        Assertions
                .assertThat(product.getTitle())
                .isEqualTo(productFromDB.getTitle());
    }

    @Test
    void remove() {
        // given
        Product product = new Product();
        product.setTitle("title");
        product.setSlug("/dfasd");
        product.setSku("dfsa");
        product.setPrice(BigDecimal.valueOf(123.4f));
        product.setDiscountRate(13.3f);
        product.setQuantity((short) 1);
        product.setAccount(accountService.findOne(accountId).get());
        productService.save(product);
        // when
        int cnt_that = productService.findAll().size();
        productService.remove(product);

        //then
        int cnt_now = productService.findAll().size();
        Assertions
                .assertThat(cnt_that - 1)
                .isEqualTo(cnt_now);
    }

    @Test
    void category() throws JsonProcessingException {
        // given
        Product product = new Product();
        product.setTitle("아이패드");
        product.setSlug("/dfasd");
        product.setSku("dfsa");
        product.setPrice(BigDecimal.valueOf(123.4f));
        product.setDiscountRate(13.3f);
        product.setQuantity((short) 1);
        product.setAccount(accountService.findOne(accountId).get());
        product.getCategories().add(catApple);
        product.getCategories().add(catTablet);

        // when
        productService.save(product);

        Product productFromDB = productService.findOne(product.getProductId()).get();

        Assertions.assertThat(productFromDB.getCategories().size()).isEqualTo(2);

        ObjectMapper mapper = new ObjectMapper();
        System.out.println("\n=====TEST OUTPUT====");
        System.out.println(mapper.writeValueAsString(productFromDB));
    }
}