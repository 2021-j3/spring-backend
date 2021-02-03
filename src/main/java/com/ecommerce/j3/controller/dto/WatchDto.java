package com.ecommerce.j3.controller.dto;

import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

public class WatchDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WatchApiRequest {
        private Long watchId;
        private Long accountId;
        private Long productId;
        private LocalDateTime recentWatch;
        private Integer watchCount;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WatchApiResponse {
        private Long watchId;
        private Account account;
        private Product product;
        private LocalDateTime recentWatch;
        private Integer watchCount;
    }
}
