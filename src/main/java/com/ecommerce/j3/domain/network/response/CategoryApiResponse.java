package com.ecommerce.j3.domain.network.response;

import com.ecommerce.j3.domain.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryApiResponse {
    private Long categoryId;
    private Category parent;
    private String title;
    private String metaTitle;
    private String slug;
    private String content;
}
