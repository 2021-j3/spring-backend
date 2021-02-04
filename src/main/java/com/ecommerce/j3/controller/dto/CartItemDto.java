package com.ecommerce.j3.controller.dto;

import com.ecommerce.j3.domain.entity.Cart;
import com.ecommerce.j3.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

public class CartItemDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CartItemApiRequest {
        private Long cartItemId;
        private Product product;
        private Cart cart;
        private String sku;
        private Integer price;
        private Integer discountRate;
        private Integer quantity;
        private Integer active;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CartItemApiResponse {
        private Long cartItemId;
        private Product product;
        private Cart cart;
        private String sku;
        private Integer price;
        private Integer discountRate;
        private Integer quantity;
        private Integer active;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

}
