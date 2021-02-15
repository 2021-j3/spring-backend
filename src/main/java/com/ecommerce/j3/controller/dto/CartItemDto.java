package com.ecommerce.j3.controller.dto;


import com.ecommerce.j3.domain.entity.Cart;
import com.ecommerce.j3.domain.entity.Product;
import lombok.*;

import java.time.LocalDateTime;

public class CartItemDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CartItemApiRequest {
        private Long cartItemId;
        private Long productId;
        private Long cartId;
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
    @Data
    public static class createCartItemApiRequest{
        private Integer quantity;
        private long productid;
    }
    @Data
    public static class createCartItemApiResponse {
        private Long carItemId;

        public createCartItemApiResponse(long id) {

            this.carItemId = id;

        }
    }

    @Data
    public static class deleteCartItemApiResponse {
        private Long cartItemId;

        public deleteCartItemApiResponse(long id) {

            this.cartItemId = id;

        }

    }

}
