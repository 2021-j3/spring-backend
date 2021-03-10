package com.ecommerce.j3.domain.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
//@Table (name = "ORDER_ITEM", schema = "SHOP")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Order order;

    private String sku;

    private Integer price;

    private Integer discountPrice;

    private Integer quantity;

    @Column(columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    //** 생성 매서드 **//
    public static OrderItem createOrderItem(Product product, Integer price, Integer quantity){
        OrderItem orderItem = OrderItem.builder()
            .product(product)
            .price(price)
            .quantity(quantity)
            .content("d")
            .discountPrice(11).build();
        product.removeQuantity(quantity);
        // FIXME: 여기서는 수정해도 반영안됨, service 에서 해당 product persist하는 과정 추가 바람

        return orderItem;
    }

    public static OrderItem FromCartItem(CartItem cartItem){
        return OrderItem.builder()
                .product(cartItem.getProduct())
                .sku(cartItem.getSku())
                .price(cartItem.getPrice())
                .discountPrice(cartItem.getDiscountPrice())
                .quantity(cartItem.getQuantity())
                .content(cartItem.getContent()).build();
    }

    public static OrderItem FromProduct(Product product, Integer quantity){
        return OrderItem.builder()
                .product(product)
                .sku(product.getSku())
                .price(product.getPrice())
                .discountPrice(product.getDiscountPrice())
                .quantity(quantity)
                .content(product.getContent()).build();
    }

    private void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    private void setPrice(Integer price) {
        this.price = price;
    }

    //** 비즈니스 로직 **
    public void setOrder(Order order){
        if (this.order != null){
            this.order.getOrderItems().remove(this);
        }
        this.order = order;
    }

    // 삭제 예정임 !!!!!//
    public void cancel(){
        getProduct().addQuantity(quantity);
    }
    public Integer getTotalPrice(){
        return getPrice()*getQuantity();
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
