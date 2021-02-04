package com.ecommerce.j3.controller.dto;


import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.Category;
import com.ecommerce.j3.domain.entity.Tag;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

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
        private Long sellerId;
        private String title;
        private String metaTitle;
        private String slug;
        private String sku;
        private Integer price;
        private Integer discountRate;
        private Integer quantity;
        private String thumbnailPath;
        private String imagePath;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private LocalDateTime startsAt;
        private LocalDateTime endsAt;
        private Set<Long> categoryIds;
        private Set<Long> tagIds;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ProductApiResponse {
        private Long productId;
        private Account seller;
        private String title;
        private String metaTitle;
        private String slug;
        private String sku;
        private Integer price;
        private Integer discountRate;
        private Integer quantity;
        private String thumbnailPath;
        private String imagePath;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private LocalDateTime startsAt;
        private LocalDateTime endsAt;
        private Set<Category> categories;
        private Set<Tag> tags;
    }

}
