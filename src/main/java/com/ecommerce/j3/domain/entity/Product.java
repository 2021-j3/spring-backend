package com.ecommerce.j3.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    // private Long accountId;
    // Product : Account ===> N : 1
    @ToString.Exclude()
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private String title;

    private String metaTitle;

    private String slug;

    private String summary;

    private String sku;

    private BigDecimal price;

    private Float discount;

    private Integer quantity;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime publishedAt;

    private LocalDateTime startsAt;

    private LocalDateTime endsAt;

    private String content;

}
