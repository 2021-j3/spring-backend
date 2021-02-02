package com.ecommerce.j3.controller.dto;


import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class SuggestionDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SuggestionApiRequest {
        private Long suggestionId;
        private Long accountId;
        private Long productId;
        private String content;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SuggestionApiResponse {
        private Long suggestionId;
        private Account account;
        private Product product;
        private String content;
    }

}
