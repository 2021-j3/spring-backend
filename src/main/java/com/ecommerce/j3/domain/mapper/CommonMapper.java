package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.*;
import com.ecommerce.j3.repository.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public class CommonMapper {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @Mapping(target="addresses", ignore = true)
    Account mapIdToAccount(Long accountId) {
        return accountRepository.findById(accountId).orElse(null);
    }

    Cart mapIdToCart(Long cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }

    List<CartItem> mapIdsToCartItems(List<Long> cartItemIds) {
        List<CartItem> cartItems = new ArrayList<>();
        for (Long cartItemId : cartItemIds) {
            cartItems.add(cartItemRepository.findById(cartItemId).orElse(null));
        }
        return cartItems;
    }

    Category mapIdToCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    Order mapIdToOrder(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    OrderItem mapIdToOrderItem(Long orderItemId) {
        return orderItemRepository.findById(orderItemId).orElse(null);
    }

    List<OrderItem> mapIdsToOrderItems(List<Long> orderItemIds) {
        // TODO: 더 괜찮은 구현 필요
        List<OrderItem> orderItems = new ArrayList<>();
        for (Long orderItemId : orderItemIds) {
            orderItems.add(orderItemRepository.findById(orderItemId).orElse(null));
        }
        return orderItems;
    }

    Product mapIdToProduct(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    Review mapIdToReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }
}
