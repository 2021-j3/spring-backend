package com.ecommerce.j3.controller.dto;


import com.ecommerce.j3.domain.entity.Order;
import com.ecommerce.j3.domain.entity.Product;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

public class OrderItemDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemApiRequest {
        private Long orderItemId;
        private Long productId;
        private Long orderId;
        private String sku;
        private Integer price;
        private Integer discountPrice;
        private Integer quantity;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemApiResponse {
        private Long orderItemId;
        private Product product;
        private Order order;
        private String sku;
        private Integer price;
        private Integer discountPrice;
        private Integer quantity;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRequestFromProduct{
        @NotNull
        private Long productId;
        @NotNull
        private Integer quantity;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRequestFromCartItem{
        private Long cartItemId;
        private Integer quantity;
    }
}
