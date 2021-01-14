package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.Account;
import com.ecommerce.j3.domain.Product;
import com.ecommerce.j3.domain.Suggestion;
import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.repository.ProductRepository;
import com.ecommerce.j3.repository.SuggestionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Transactional
@SpringBootTest
class SuggestionServiceTest {
    @Autowired AccountService accountService;
    @Autowired AccountRepository accountRepository;
    @Autowired SuggestionService suggestionService;
    @Autowired SuggestionRepository suggestionRepository;
    @Autowired ProductService productService;
    @Autowired ProductRepository productRepository;

    Long accountId;
    Long productId1;
    Long productId2;

    @BeforeEach
    void beforeEach(){
        // given
        Account account = new Account();
        account.setNickname("CART ITEM TEST");
        account.setPasswordHash("passHash");
        account.setEmail("example1@mail.com");
        account.setPhoneNumber("000-000-000");
        accountService.save(account);
        accountId = account.getAccountId();

        // given
        Product product = new Product();
        product.setTitle("product1");
        product.setSlug("/slug1");
        product.setSku("sku1");
        product.setPrice(BigDecimal.valueOf(123.4f));
        product.setDiscountRate(13.3f);
        product.setQuantity((short)1);
        product.setAccount(accountService.findOne(accountId).get());
        // when
        productService.save(product);
        productId1 = product.getProductId();

        // given
        Product product2 = new Product();
        product2.setTitle("product2");
        product2.setSlug("/slug2");
        product2.setSku("sku2");
        product2.setPrice(BigDecimal.valueOf(123.4f));
        product2.setDiscountRate(13.3f);
        product2.setQuantity((short)1);
        product2.setAccount(accountService.findOne(accountId).get());
        // when
        productService.save(product2);
        productId2 = product2.getProductId();
    }

    @Test
    void save(){
        // given
        Suggestion suggestion = new Suggestion();
        suggestion.setAccount(accountService.findOne(accountId).get());
        suggestion.setProduct(productService.findOne(productId1).get());
        // when
        suggestionService.save(suggestion);

        //then
        Suggestion suggestionFromDB = suggestionRepository.getOne(suggestion.getSuggestionId());
        Assertions
                .assertThat(suggestion.getAccount())
                .isEqualTo(suggestionFromDB.getAccount());
    }

    @Test
    void update() {
        // given
        Suggestion suggestion = new Suggestion();
        suggestion.setAccount(accountService.findOne(accountId).get());
        suggestion.setProduct(productService.findOne(productId1).get());
        suggestionService.save(suggestion);
        // when
        suggestion.setProduct(productService.findOne(productId2).get());
        suggestionService.update(suggestion);

        //then
        Suggestion suggestionFromDB = suggestionRepository.getOne(suggestion.getSuggestionId());
        Assertions
                .assertThat(suggestion.getProduct())
                .isEqualTo(suggestionFromDB.getProduct());
    }

    @Test
    void findOneById() {
        // given
        Suggestion suggestion = new Suggestion();
        suggestion.setAccount(accountService.findOne(accountId).get());
        suggestion.setProduct(productService.findOne(productId1).get());
        suggestionService.save(suggestion);

        //then
        Suggestion suggestionFromDB = suggestionRepository.getOne(suggestion.getSuggestionId());
        Assertions
                .assertThat(suggestion.getAccount())
                .isEqualTo(suggestionFromDB.getAccount());
    }

    @Test
    void remove() {
        // given
        Suggestion suggestion = new Suggestion();
        suggestion.setAccount(accountService.findOne(accountId).get());
        suggestion.setProduct(productService.findOne(productId1).get());
        suggestionService.save(suggestion);
        // when
        List<Suggestion> suggestionList = suggestionService.findAll();
        int cnt_that = suggestionList.size();
        suggestionService.remove(suggestion);

        //then
        int cnt_now = suggestionService.findAll().size();
        Assertions
                .assertThat(cnt_that - 1)
                .isEqualTo(cnt_now);
    }
}