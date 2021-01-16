package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.Account;
import com.ecommerce.j3.domain.CartItem;
import com.ecommerce.j3.domain.Product;
import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.repository.CartItemRepository;
import com.ecommerce.j3.repository.ProductRepository;
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
class CartItemServiceTest {
    @Autowired AccountService accountService;
    @Autowired AccountRepository accountRepository;
    @Autowired
    CartItemService cartItemService;
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;


    Long accountId;
    Long productId;

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
        product.setTitle("title");
        product.setSlug("/dfasd");
        product.setSku("dfsa");
        product.setPrice(BigDecimal.valueOf(123.4f));
        product.setDiscountRate(13.3f);
        product.setQuantity((short)1);
        product.setAccount(accountService.findOne(accountId).get());
        // when
        productService.save(product);
        productId = product.getProductId();
    }

    @Test
    void save(){
        // given
        CartItem cartItem = new CartItem();
        cartItem.setSku("dfsa");
        cartItem.setPrice(123.4f);
        cartItem.setDiscountRate(13.3f);
        cartItem.setQuantity((short)1);
//        cartItem.setActive((byte)1);

        cartItem.setAccount(accountService.findOne(accountId).get());
        cartItem.setProduct(productService.findOne(productId).get());
        // when
        cartItemService.save(cartItem);

        //then
        CartItem cartItemFromDB = cartItemRepository.getOne(cartItem.getCartItemId());
        Assertions
                .assertThat(cartItem.getSku())
                .isEqualTo(cartItemFromDB.getSku());
    }

    @Test
    void update() {
        // given
        CartItem cartItem = new CartItem();
        cartItem.setSku("dfsa");
        cartItem.setPrice(123.4f);
        cartItem.setDiscountRate(13.3f);
        cartItem.setQuantity((short)1);
//        cartItem.setActive((byte)1);

        cartItem.setAccount(accountService.findOne(accountId).get());
        cartItem.setProduct(productService.findOne(productId).get());
        cartItemService.save(cartItem);
        // when
        cartItem.setSku("new");
        cartItemService.update(cartItem);

        //then
        CartItem cartItemFromDB = cartItemRepository.getOne(cartItem.getCartItemId());
        Assertions
                .assertThat(cartItem.getSku())
                .isEqualTo(cartItemFromDB.getSku());
    }

    @Test
    void findOneById() {
        // given
        CartItem cartItem = new CartItem();
        cartItem.setSku("dfsa");
        cartItem.setPrice(123.4f);
        cartItem.setDiscountRate(13.3f);
        cartItem.setQuantity((short)1);
//        cartItem.setActive((byte)1);

        cartItem.setAccount(accountService.findOne(accountId).get());
        cartItem.setProduct(productService.findOne(productId).get());
        cartItemService.save(cartItem);

        //then
        CartItem cartItemFromDB = cartItemRepository.getOne(cartItem.getCartItemId());
        Assertions
                .assertThat(cartItem.getSku())
                .isEqualTo(cartItemFromDB.getSku());
    }

    @Test
    void remove() {
        // given
        CartItem cartItem = new CartItem();
        cartItem.setSku("dfsa");
        cartItem.setPrice(123.4f);
        cartItem.setDiscountRate(13.3f);
        cartItem.setQuantity((short)1);
//        cartItem.setActive((byte)1);

        cartItem.setAccount(accountService.findOne(accountId).get());
        cartItem.setProduct(productService.findOne(productId).get());
        cartItemService.save(cartItem);
        // when
        List<CartItem> cartItemList = cartItemService.findAll();
        int cnt_that = cartItemList.size();
        cartItemService.remove(cartItem);

        //then
        int cnt_now = cartItemService.findAll().size();
        Assertions
                .assertThat(cnt_that - 1)
                .isEqualTo(cnt_now);
    }
}