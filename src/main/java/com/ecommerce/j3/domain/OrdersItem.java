package com.ecommerce.j3.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name="orders_item")
@Getter
@Setter
public class OrdersItem {
    @Id
    @Column(name = "orders_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Orders orders;
    private String sku;
    private float price;
    private float discount;
    private short quantity;
    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(name = "upated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;
    private String text;
}
