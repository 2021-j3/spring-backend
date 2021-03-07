package com.ecommerce.j3.controller.dto;


import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.Order;
import com.ecommerce.j3.domain.entity.PaymentStatus;
import com.ecommerce.j3.domain.entity.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

public class PaymentDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PaymentApiRequest {
        private Long paymentId;
        private Long accountId;
        private Long orderId;
        private String code;
        private PaymentType type;
        private PaymentStatus status;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PaymentApiResponse {
        private Long paymentId;
        private Account account;
        private Order order;
        private String code;
        private PaymentType type;
        private PaymentStatus status;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
