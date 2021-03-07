package com.ecommerce.j3.controller.dto;


import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.OrderItem;
import com.ecommerce.j3.domain.entity.Review;
import lombok.*;

import java.time.LocalDateTime;

public class ReviewDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewApiRequest {
        private Long reviewId;
        private Long parentId;
        private Long orderItemId;
        private Long accountId;
        private Integer rate; // set 1
        private String title;
        private String content;
        private LocalDateTime createdAt;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewApiResponse {
        private Long reviewId;
        private Review parent;
        private OrderItem orderItem;
        private Account account;
        private Integer rate; // set 1
        private String title;
        private String content;
        private LocalDateTime createdAt;
    }

    @Data
    public static class ReviewDtoForSave {
        private Long reviewId;
        private Long parentId;
        private Long orderItemId;
        private Long accountId;
        private Integer rate; // set 1
        private String title;
        private String content;
        private LocalDateTime createdAt;
    }
}
