package com.ecommerce.j3.controller.dto;


import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.Category;
import com.ecommerce.j3.domain.entity.Tag;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
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
        private Integer discountPrice;
        private Integer quantity;
        private Integer soldCount;
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
        private Long sellerId;
        private String title;
        private String metaTitle;
        private String slug;
        private String sku;
        private Integer price;
        private Integer discountPrice;
        private Integer quantity;
        private Integer soldCount;
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

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SearchResultItem {
        private Long productId;
        private Long sellerId;
        private String title;
        private String slug;
        private Integer price;
        private Integer discountPrice;
        private Integer quantity;
        private Integer soldCount;
        private String thumbnailPath;
        private String imagePath;
        private LocalDateTime createdAt;
    }

    @Data
    @AllArgsConstructor @Builder
    public static class SearchResult{
        Long total;
        List<SearchResultItem> contents;
    }

    @Data
    @AllArgsConstructor @Builder
    public static class SearchCondition{
        private String query;
        private Integer minPrice;
        private Integer maxPrice;
        private List<Long> categoryIds;
        private List<Long> tagIds;
    }
}
