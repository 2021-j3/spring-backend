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
import java.util.ArrayList;
import java.util.List;

@Transactional
@SpringBootTest
class ProductServiceTest {
    @Autowired AccountService accountService;
    @Autowired AccountRepository accountRepository;
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;

    @Autowired
            CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;

     Long accountId;

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
    }

    @Test
    void save(){
        // given
        Product product = new Product();
        product.setTitle("title");
        product.setSlug("/dfasd");
        product.setSku("dfsa");
        product.setPrice(BigDecimal.valueOf(123.4f));
        product.setDiscountRate(13.3f);
        product.setQuantity((short)1);
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
        product.setQuantity((short)1);
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
        product.setQuantity((short)1);
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
        product.setQuantity((short)1);
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
    void category(){
        ObjectMapper mapper = new ObjectMapper();

        // given
        Category category4 = new Category();
        category4.setTitle("갤탭");
        category4.setSlug("fasdf");
        category4.setContent("content");
        categoryService.save(category4);

        try {
            System.out.println("category4: " + mapper.writeValueAsString(category4));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        Product product = new Product();
        product.setTitle("title");
        product.setSlug("/dfasd");
        product.setSku("dfsa");
        product.setPrice(BigDecimal.valueOf(123.4f));
        product.setDiscountRate(13.3f);
        product.setQuantity((short)1);
        product.setAccount(accountService.findOne(accountId).get());

        try {
            System.out.println("product :" + mapper.writeValueAsString(product));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //when
        List<Category> catList = new ArrayList<Category>();
        catList.add(category4);
        product.setCategoryList(catList);

        try {
            System.out.println("product with category: " + mapper.writeValueAsString(product));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        productService.save(product);
        // when
        int cnt_that = productService.findAll().size();
        productService.remove(product);

        Product productFromDB = productService.findOne(product.getProductId()).get();
        List<Category> catList2 = productFromDB.getCategoryList();
        for (Category cat:
             catList2) {
            System.out.println(cat.getTitle());
        }
    }
}