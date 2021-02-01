package com.ecommerce.j3.domain.network.request;

import com.ecommerce.j3.domain.entity.Account;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductApiRequest {
    private Long productId;
    private Account account;
    private String title;
    private String metaTitle;
    private String slug;
    private String sku;
    private BigDecimal price;
    private Float discountRate;
    private Short quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime startsAt;
    private LocalDateTime endsAt;
    private String content;
}
