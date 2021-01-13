package com.ecommerce.j3.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Account account;
    private String title;
    private String meta_title;
    private String slug;
    private String sku;
    private BigDecimal price;
    private float discountRate;
    private short quantity;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
    private Timestamp startsAt;
    private Timestamp endsAt;
    @Column(columnDefinition = "TEXT")
    private String content;

//    @ManyToMany
//    @JoinTable(
//            name = "product_category"
//            ,joinColumns = @JoinColumn(name = "product_id"),
//            inverseJoinColumns = @JoinColumn(name = "category_id")
//    )
//    private List<Category> categoryList = new ArrayList<>();
//    @ManyToMany
//    @JoinTable(
//            name = "product_tag",
//            joinColumns = @JoinColumn(name = "product_id"),
//            inverseJoinColumns = @JoinColumn(name = "tag_id")
//    )
}