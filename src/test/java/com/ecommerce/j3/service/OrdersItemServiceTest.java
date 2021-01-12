package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.Account;
import com.ecommerce.j3.domain.Orders;
import com.ecommerce.j3.domain.OrdersItem;
import com.ecommerce.j3.domain.Product;
import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.repository.OrdersItemRepository;
import com.ecommerce.j3.repository.OrdersRepository;
import com.ecommerce.j3.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Transactional
@SpringBootTest
class OrdersItemServiceTest {
    @Autowired AccountService accountService;
    @Autowired AccountRepository accountRepository;
    @Autowired
    OrdersItemService ordersItemService;
    @Autowired
    OrdersItemRepository ordersItemRepository;
    @Autowired
    OrdersService ordersService;
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;


    Long accountId;
    Long ordersId;
    Long productId;

    @BeforeEach
    void beforeEach(){
        // given
        Account account = new Account();
        account.setNickname("nick");
        account.setPasswordHash("passHash");
        account.setEmail("example1@mail.com");
        account.setPhoneNumber("000-000-000");
        accountService.save(account);
        accountId = account.getId();

        // given
        Orders orders = new Orders();
        orders.setSessionId("2132");
        orders.setToken("4124");
        orders.setStatus((short) 1);
        orders.setItemPriceTotal(BigDecimal.valueOf(124));
        orders.setItemDiscount(1.23f);
        orders.setTax(0.213f);
        orders.setShipping(231.214f);
        orders.setUserDiscount(123.13f);
        orders.setGrandTotal(213.31f);
        orders.setAccount(accountService.findOneById(accountId).get());
        // when
        ordersService.save(orders);
        ordersId = orders.getId();

        // given
        Product product = new Product();
        product.setTitle("title");
        product.setSlug("/dfasd");
        product.setSku("dfsa");
        product.setPrice(123.4f);
        product.setDiscount(13.3f);
        product.setQuantity((short)1);
        product.setAccount(accountService.findOneById(accountId).get());
        // when
        productService.save(product);
        productId = product.getId();
    }

    @Test
    void save(){
        // given
        OrdersItem ordersItem = new OrdersItem();
        ordersItem.setSku("dfsa");
        ordersItem.setPrice(123.4f);
        ordersItem.setDiscount(13.3f);
        ordersItem.setQuantity((short)1);
        ordersItem.setOrders(ordersService.findOneById(ordersId).get());
        ordersItem.setProduct(productService.findOneById(productId).get());
        // when
        ordersItemService.save(ordersItem);

        //then
        OrdersItem ordersItemFromDB = ordersItemRepository.getOne(ordersItem.getId());
        Assertions
                .assertThat(ordersItem.getSku())
                .isEqualTo(ordersItemFromDB.getSku());
    }

    @Test
    void update() {
        // given
        OrdersItem ordersItem = new OrdersItem();
        ordersItem.setSku("dfsa");
        ordersItem.setPrice(123.4f);
        ordersItem.setDiscount(13.3f);
        ordersItem.setQuantity((short)1);
        ordersItem.setOrders(ordersService.findOneById(ordersId).get());
        ordersItem.setProduct(productService.findOneById(productId).get());
        ordersItemService.save(ordersItem);
        // when
        ordersItem.setSku("new");
        ordersItemService.update(ordersItem);

        //then
        OrdersItem ordersItemFromDB = ordersItemRepository.getOne(ordersItem.getId());
        Assertions
                .assertThat(ordersItem.getSku())
                .isEqualTo(ordersItemFromDB.getSku());
    }

    @Test
    void findOneById() {
        // given
        OrdersItem ordersItem = new OrdersItem();
        ordersItem.setSku("dfsa");
        ordersItem.setPrice(123.4f);
        ordersItem.setDiscount(13.3f);
        ordersItem.setQuantity((short)1);
        ordersItem.setOrders(ordersService.findOneById(ordersId).get());
        ordersItem.setProduct(productService.findOneById(productId).get());
        ordersItemService.save(ordersItem);

        //then
        OrdersItem ordersItemFromDB = ordersItemRepository.getOne(ordersItem.getId());
        Assertions
                .assertThat(ordersItem.getSku())
                .isEqualTo(ordersItemFromDB.getSku());
    }

    @Test
    void remove() {
        // given
        OrdersItem ordersItem = new OrdersItem();
        ordersItem.setSku("dfsa");
        ordersItem.setPrice(123.4f);
        ordersItem.setDiscount(13.3f);
        ordersItem.setQuantity((short)1);
        ordersItem.setOrders(ordersService.findOneById(ordersId).get());
        ordersItem.setProduct(productService.findOneById(productId).get());
        ordersItemService.save(ordersItem);
        // when
        int cnt_that = ordersItemService.findAll().size();
        ordersItemService.remove(ordersItem);

        //then
        int cnt_now = ordersItemService.findAll().size();
        Assertions
                .assertThat(cnt_that - 1)
                .isEqualTo(cnt_now);
    }
}