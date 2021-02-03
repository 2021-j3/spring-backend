package com.ecommerce.j3.controller.dto;


import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.OrderItem;
import com.ecommerce.j3.domain.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderApiRequest {
        private Long ordersId;
        private Long accountId;
        public List<Long> orderItemIds;
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
        private String roadAddress;
        private String address;
        private String city;
        private String province;
        private String country;
        private Integer zipCode;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderApiResponse {
        private Long ordersId;
        private Account account;
        public List<OrderItem> orderItems;
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
        private String roadAddress;
        ;
        private String address;
        private String city;
        private String province;
        private String country;
        private Integer zipCode;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

}
