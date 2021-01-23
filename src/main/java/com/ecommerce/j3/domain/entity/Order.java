package com.ecommerce.j3.domain.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name="orders") @Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy="order",cascade = CascadeType.ALL)    // 01-18 Megan
    private List<OrderItem> orderItems;

    private String sessionId;

    private String token;

    @Column(columnDefinition = "enum('READY', 'ORDER', 'CANCEL')")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Integer itemPriceTotal;

    private Integer itemDiscount;

    private Integer tax;

    private Integer shipping;

    private Integer userDiscount;

    private Integer grandTotal;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private String roadAddress;;

    private String address;

    private String city;

    private String province;

    private String country;

    private Integer zipCode;

    @Column(columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    //** 연관 관계 메서드 **//
    public void setAccount(Account account){
        this.account = account;
        //account.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    //** 생성 메서드 **//
    public static Order createorder(Account account,OrderItem... orderItems) {
        Order order = new Order();
        order.setAccount(account);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
//        order.setStatus((short) 1);   // 1 : ORDER   2 : CANCEL
        order.setStatus(OrderStatus.ORDER);
        //  order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //** 비즈니스 로직 **//
    // 주문 취소
    public void cancel(){
//        this.setStatus((short)2);
        this.setStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem:orderItems){
            orderItem.cancel();
        }
    }

    //  주문 조회
    public float getgrandTotal(){
        Integer totalprice  = 0;
        for(OrderItem orderItem: orderItems){
            grandTotal += orderItem.getTotalPrice();
        }
        return totalprice;
    }

    public void setStatus(OrderStatus status){
        this.status = status;
    }
}
