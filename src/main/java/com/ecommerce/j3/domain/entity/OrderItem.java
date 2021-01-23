package com.ecommerce.j3.domain.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private String sku;

    private Integer price;

    private Integer discountRate;

    private Integer quantity;

    @Column(columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    //** 생성 매서드 **//
    public static OrderItem createOrderItem(Product product, Integer price, Integer quantity){
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setPrice(price);
        orderItem.setQuantity(quantity);
        product.removeQuantity(quantity);

        return orderItem;
    }

    private void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    private void setPrice(Integer price) {
        this.price = price;
    }

    //** 비즈니스 로직 **//
    public void cancel(){
        getProduct().addQuantity(quantity);
    }
    public Integer getTotalPrice(){
        return getPrice()*getQuantity();
    }

    public void setProduct(Product product) {
        this.product = product;
    }



    public void setOrder(Order order){
        this.order = order;
    }
}
