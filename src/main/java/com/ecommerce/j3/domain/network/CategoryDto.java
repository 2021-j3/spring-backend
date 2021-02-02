package com.ecommerce.j3.domain.network;

import com.ecommerce.j3.domain.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CategoryDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategoryApiRequest {
        private Long categoryId;
        private Category parent;
        private String title;
        private String metaTitle;
        private String slug;
        private String content;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategoryApiResponse {
        private Long categoryId;
        private Category parent;
        private String title;
        private String metaTitle;
        private String slug;
        private String content;
    }

}
