package com.ecommerce.j3.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity @Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Suggestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long suggestionId;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(columnDefinition = "TEXT")
    private String content;
}
