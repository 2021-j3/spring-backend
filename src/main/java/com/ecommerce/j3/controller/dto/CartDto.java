package com.ecommerce.j3.controller.dto;


import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.CartItem;
import com.ecommerce.j3.domain.entity.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class CartDto {
    /**
     * 기본 crud request
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CartApiRequest {
        private Long cartId;
        private Long accountId;
        private List<Long> cartItemIds;
        private String sessionId;
        private String token;
        private OrderStatus status;
        private Integer itemPriceTotal;
        private Integer itemDiscount;
        private Integer tax;
        private Integer shipping;
        private Integer userDiscount;
        private Integer grandTotal;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String email;
        private String roadAddress;;
        private String address;
        private String city;
        private String province;
        private String country;
        private Integer zipCode;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
    /**
     * 기본 crud response
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CartApiResponse {
        private Long cartId;
        private Account account;
        private List<CartItem> cartItems;
        private String sessionId;
        private String token;
        private OrderStatus status;
        private Integer itemPriceTotal;
        private Integer itemDiscount;
        private Integer tax;
        private Integer shipping;
        private Integer userDiscount;
        private Integer grandTotal;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String email;
        private String roadAddress;;
        private String address;
        private String city;
        private String province;
        private String country;
        private Integer zipCode;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Data
    public static class readCartApiResponse{
        private Long cartId;
        private Account account;
        private List<CartItem> cartItems;
        private String sessionId;
        private String token;
        private OrderStatus status;
        private Integer itemPriceTotal;
        private Integer itemDiscount;
        private Integer tax;
        private Integer shipping;
        private Integer userDiscount;
        private Integer grandTotal;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String email;
        private String roadAddress;;
        private String address;
        private String city;
        private String province;
        private String country;
        private Integer zipCode;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        public readCartApiResponse(long id){

            this.cartId = id;

        }
    }
    @Data
    public static class createCartApiResponse{
        private Long cartId;

        public createCartApiResponse(long id){

            this.cartId = id;

        }
    }
    @Data
    public static class deleteCartApiResponse{
        private Long cartId;

        public deleteCartApiResponse(long id){

            this.cartId = id;

        }
    }
}
