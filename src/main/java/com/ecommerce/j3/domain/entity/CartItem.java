package com.ecommerce.j3.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    // private Long accountId;
    // CartItem : Account ===> N : 1
    @ToString.Exclude()
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    // private Long productId;
    // CartItem : Product ===> N : 1
    @ToString.Exclude()
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String sku;

    private BigDecimal price;

    private Float discountRate;

    private Integer quantity;

//    private Integer active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String content;

}