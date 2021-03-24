package com.ecommerce.j3.controller.dto;


import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.OrderItem;
import com.ecommerce.j3.domain.entity.OrderStatus;
import lombok.*;


import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

public class OrderDto {
    @Getter @Builder @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PayInfo {
        private Integer itemPriceTotal;
        private Integer itemDiscount;
        private Integer tax;
        private Integer shipping;
        private Integer userDiscount;
        private Integer grandTotal;
    }

    @Getter @Builder @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserInfo{
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
    }

    @Getter @Builder @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderApiRequest {
        private Long ordersId;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "account_id")
        private Account account;

        public List<Long> orderItemIds;
        private OrderStatus status;
        private PayInfo payInfo;
        private UserInfo userInfo;
    }

    @Getter @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderApiResponse {
        private Long ordersId;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "account_id")
        private Account account;

        public List<OrderItem> orderItems;
        private OrderStatus status;
        private PayInfo payInfo;
        private UserInfo userInfo;
    }

    @Getter @Builder @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemCreateRequestByCartItem {
        private Long accountId;
        private Long CartItemId;
        private Integer quantity;
    }

    @Getter @Builder @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemCreateResponseByCartItem {
        private Long accountId;
        private Long CartItemId;
        private Integer quantity;
    }

    @Getter @Builder @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemCreateRequestByProduct {
        private Long accountId;
        private Long productId;
        private Integer quantity;
    }

    @Getter @Builder @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemCreateResponseByProduct {
        private Long accountId;
        private Long productId;
        private Integer quantity;
    }

    @Getter @Builder @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderCreateRequestByItems {
        private OrderItemCreateRequestByCartItem orderItemCreateRequestByCartItem;
    }

    @Getter @Builder @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderCreateRequestByProduct{
        OrderItemCreateRequestByProduct orderItemCreateRequestByProduct;
    }

}

// target ===> Entity에 있는 필드 이름
// source ===> Dto에 있는 필드 이름
// https://stackoverflow.com/questions/45037502/mapstruct-update-existing-field-of-entity-using-data-from-dto