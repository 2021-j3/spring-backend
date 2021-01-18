package com.ecommerce.j3.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    // private Long productId;
    // Product : OrderItem ===> N : 1
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // private Long ordersId;
    // Orders : OrderItem ===> N : 1
    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Order orders;

    private String sku;

    private Float price;

    private Float discount;

    private Integer quantity;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(columnDefinition = "TEXT")
    private String content;

}
