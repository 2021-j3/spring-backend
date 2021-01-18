package com.ecommerce.j3.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Suggestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long suggestionId;

    // private Long accountId;
    // Suggestion : Account ===> N : 1
    @ToString.Exclude()
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    // private Long productId;
    // Suggestion : Product ===> N : 1
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(columnDefinition = "TEXT")
    private String content;

}
