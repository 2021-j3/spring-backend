package com.ecommerce.j3.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name="review")
public class Review {
    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_id")
    private Long accountId;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "parent_id")
    private Long parentId;
    private short rate;
    private String title;
    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(name = "published_at")
    private byte publishedAt;
    @Column(name = "public_at")
    private Timestamp publicAt;
    @Column(columnDefinition = "TEXT")
    private String content;
}