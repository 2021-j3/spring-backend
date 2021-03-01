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
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    private String sku;

    /**
     *  2021-03-01 quantity 는 카트 아이템이 가리키는 제품의 총 가격입니다
     */
    private Integer price;
    /**
     *  2021-03-01 quantity 는 카트 아이템이 가리키는 제품의 총 할인가격입니다
     */
    private Integer discountPrice;
    /**
     *  2021-03-01 quantity 는 카트에 담은 product 의 개수입니다
     */
    private Integer quantity;

    private Integer active;

    @Column(columnDefinition = "TEXT")
    private String content; // outOfStock: Bool

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public static CartItem createCartItem(Cart cart,Integer quantity, Product product){
        CartItem cartItem = new CartItem();
        cartItem.cart = cart;
        cartItem.product = product;
        cartItem.price = product.getPrice();
        cartItem.quantity = quantity;

        return cartItem;
    }
}
