package com.ecommerce.j3.domain.entity;

import com.ecommerce.j3.exception.NotEnoughStockException;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity @Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Account seller;

    @NonNull
    private String title;

    @NonNull
    private String metaTitle;

    @NonNull
    private String slug;

    private String sku;

    private Integer price;

    private Integer discountRate;

    private Integer quantity;

    private String thumbnailPath;

    private String imagePath;

    @Column(columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDateTime startsAt;

    private LocalDateTime endsAt;


    @ManyToMany
    @JoinTable(
            name = "product_category"
            ,joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

    @ManyToMany
    @JoinTable(
            name = "product_tag",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;

    //** 비즈니스 로직 **//
    // 재고 증가
    public void addQuantity(Integer quantity){
        this.quantity += quantity;
    }
    public void removeQuantity(Integer quantity){
        Integer restquantity = this.quantity = quantity;
        if(restquantity < 0 ){
            throw new NotEnoughStockException("Need More Stock");
        }
        this.quantity = restquantity;
    }
}