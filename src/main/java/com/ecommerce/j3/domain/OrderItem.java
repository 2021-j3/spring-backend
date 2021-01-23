package com.ecommerce.j3.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity(name="order_item")
@Getter @Setter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private String sku;
    private float price;
    private float discountRate;
    private short quantity;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @Column(columnDefinition = "TEXT")
    private String content;

    //** 생성 매서드 **//
    public static OrderItem createOrderItem(Product product, float price, short quantity){
    OrderItem orderItem = new OrderItem();
    orderItem.setProduct(product);
    orderItem.setPrice(price);
    orderItem.setQuantity(quantity);
    product.removeQuantity(quantity);

    return orderItem;
    }
    //** 비즈니스 로직 **//
    public void cancel(){
        getProduct().addQuantity(quantity);
    }
    public float getTotalPrice(){
        return getPrice()*getQuantity();
    }


}
