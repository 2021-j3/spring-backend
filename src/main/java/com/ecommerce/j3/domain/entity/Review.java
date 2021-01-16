package com.ecommerce.j3.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ToString.Exclude()
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ToString.Exclude()
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ToString.Exclude()
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Review parent;

    private Float rate;

    private String title;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TEXT")
    private String content;
}
