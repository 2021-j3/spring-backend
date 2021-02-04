package com.ecommerce.j3.controller.dto;

import com.ecommerce.j3.domain.entity.Account;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductDto {
    /**
     * 기본 crud request
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ProductApiRequest {

        private Long productId;

        private Account account;

        private String title;
        private String metaTitle;
        private String slug;
        private String sku;
        private BigDecimal price;
        private Float discountRate;
        private Short quantity;

        @CreationTimestamp
        private LocalDateTime createdAt;

        @UpdateTimestamp
        private LocalDateTime updatedAt;

        private LocalDateTime startsAt;
        private LocalDateTime endsAt;

        @Column(columnDefinition = "TEXT")
        private String content;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ProductApiResponse {
        private Long productId;

        private Account account;

        private String title;
        private String metaTitle;
        private String slug;
        private String sku;
        private BigDecimal price;
        private Float discountRate;
        private Short quantity;

        @CreationTimestamp
        private LocalDateTime createdAt;

        @UpdateTimestamp
        private LocalDateTime updatedAt;

        private LocalDateTime startsAt;
        private LocalDateTime endsAt;

        @Column(columnDefinition = "TEXT")
        private String content;
    }

}
