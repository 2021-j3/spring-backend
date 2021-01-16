package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.Account;
import com.ecommerce.j3.domain.Order;
import com.ecommerce.j3.domain.OrderItem;
import com.ecommerce.j3.domain.Product;
import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.repository.OrderItemRepository;
import com.ecommerce.j3.repository.OrderRepository;
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
class OrderItemServiceTest {
    @Autowired AccountService accountService;
    @Autowired AccountRepository accountRepository;
    @Autowired OrderItemService orderItemService;
    @Autowired OrderItemRepository orderItemRepository;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired ProductService productService;
    @Autowired ProductRepository productRepository;


    Long accountId;
    Long ordersId;
    Long productId;

    @BeforeEach
    void beforeEach(){
        // given
        Account account = new Account();
        account.setNickname("ORDER ITEM");
        account.setPasswordHash("passHash");
        account.setEmail("example1@mail.com");
        account.setPhoneNumber("000-000-000");
        accountService.save(account);
        accountId = account.getAccountId();

        // given
        Order order = new Order();
        order.setSessionId("2132");
        order.setToken("4124");
        order.setStatus((short) 1);
        order.setItemPriceTotal(BigDecimal.valueOf(124));
        order.setItemDiscount(1.23f);
        order.setTax(0.213f);
        order.setShipping(BigDecimal.valueOf(231.214f));
        order.setUserDiscount(123.13f);
        order.setGrandTotal(213.31f);
        order.setAccount(accountService.findOne(accountId).get());
        order.setZipCode(84984);
        // when
        orderService.save(order);
        ordersId = order.getOrderId();

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
        OrderItem orderItem = new OrderItem();
        orderItem.setSku("dfsa");
        orderItem.setPrice(123.4f);
        orderItem.setDiscountRate(13.3f);
        orderItem.setQuantity((short)1);
        orderItem.setOrder(orderService.findOne(ordersId).get());
        orderItem.setProduct(productService.findOne(productId).get());
        // when
        orderItemService.save(orderItem);

        //then
        OrderItem orderItemFromDB = orderItemRepository.getOne(orderItem.getOrderItemId());
        Assertions
                .assertThat(orderItem.getSku())
                .isEqualTo(orderItemFromDB.getSku());
    }

    @Test
    void update() {
        // given
        OrderItem orderItem = new OrderItem();
        orderItem.setSku("dfsa");
        orderItem.setPrice(123.4f);
        orderItem.setDiscountRate(13.3f);
        orderItem.setQuantity((short)1);
        orderItem.setOrder(orderService.findOne(ordersId).get());
        orderItem.setProduct(productService.findOne(productId).get());
        orderItemService.save(orderItem);
        // when
        orderItem.setSku("new");
        orderItemService.update(orderItem);

        //then
        OrderItem orderItemFromDB = orderItemRepository.getOne(orderItem.getOrderItemId());
        Assertions
                .assertThat(orderItem.getSku())
                .isEqualTo(orderItemFromDB.getSku());
    }

    @Test
    void findOneById() {
        // given
        OrderItem orderItem = new OrderItem();
        orderItem.setSku("dfsa");
        orderItem.setPrice(123.4f);
        orderItem.setDiscountRate(13.3f);
        orderItem.setQuantity((short)1);
        orderItem.setOrder(orderService.findOne(ordersId).get());
        orderItem.setProduct(productService.findOne(productId).get());
        orderItemService.save(orderItem);

        //then
        OrderItem orderItemFromDB = orderItemRepository.getOne(orderItem.getOrderItemId());
        Assertions
                .assertThat(orderItem.getSku())
                .isEqualTo(orderItemFromDB.getSku());
    }

    @Test
    void remove() {
        // given
        OrderItem orderItem = new OrderItem();
        orderItem.setSku("dfsa");
        orderItem.setPrice(123.4f);
        orderItem.setDiscountRate(13.3f);
        orderItem.setQuantity((short)1);
        orderItem.setOrder(orderService.findOne(ordersId).get());
        orderItem.setProduct(productService.findOne(productId).get());
        orderItemService.save(orderItem);
        // when
        int cnt_that = orderItemService.findAll().size();
        orderItemService.remove(orderItem);

        //then
        int cnt_now = orderItemService.findAll().size();
        Assertions
                .assertThat(cnt_that - 1)
                .isEqualTo(cnt_now);
    }
}