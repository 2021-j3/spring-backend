package jpabook.jpashop.domain;

import javax.persistence.*;

@Entity
public class CartItem {
    @Id
    @GeneratedValue
    private long cart_item_id;

    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    private Product product;

    @ManyToOne
    @JoinColumn(name ="ACCOUNT_ID")
    private Account account;

    private float price;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private ActiveType active ;  // default : Inactive.
}
