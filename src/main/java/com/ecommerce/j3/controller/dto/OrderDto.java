package com.ecommerce.j3.controller.dto;


import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.Order;
import com.ecommerce.j3.domain.entity.OrderItem;
import com.ecommerce.j3.domain.entity.OrderStatus;
import com.ecommerce.j3.domain.mapper.CommonMapper;
import com.ecommerce.j3.domain.mapper.DefaultMapper;
import lombok.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;



import java.time.LocalDateTime;
import java.util.List;

public class OrderDto {

//    @Getter @Builder
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class PayInfo {
//        private Integer itemPriceTotal;
//        private Integer itemDiscount;
//        private Integer tax;
//        private Integer shipping;
//        private Integer userDiscount;
//        private Integer grandTotal;
//    }
//
//    @Getter @Builder
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class UserInfo{
//        private String firstName;
//        private String lastName;
//        private String phoneNumber;
//        private String email;
//        private String roadAddress;
//        private String address;
//        private String city;
//        private String province;
//        private String country;
//        private Integer zipCode;
//    }

    @Getter @Builder
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

    @Getter @Builder
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

// target ===> Entity에 있는 필드 이름
// source ===> Dto에 있는 필드 이름
// https://stackoverflow.com/questions/45037502/mapstruct-update-existing-field-of-entity-using-data-from-dto