package com.ecommerce.j3.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class TagDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TagApiRequest{
        private Long tagId;
        private String title;
        private String metaTitle;
        private String slug;
        private String content;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TagApiResponse{
        private Long tagId;
        private String title;
        private String metaTitle;
        private String slug;
        private String content;
    }
}
