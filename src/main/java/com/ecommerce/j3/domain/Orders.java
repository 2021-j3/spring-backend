package com.ecommerce.j3.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity(name = "orders")
@Getter
@Setter
public class Orders {
    @Id
    @Column(name = "orders_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "session_id")
    private String sessionId;
    private String token;
    private short status;
    @Column(name = "item_price_total")
    private BigDecimal itemPriceTotal;
    @Column(name = "item_discount")
    private float itemDiscount;
    private float tax;
    private float shipping;
    @Column(name = "user_discount")
    private float userDiscount;
    @Column(name = "grandTotal")
    private float grandTotal;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;
    @Column(name = "road_address")
    private String roadAddress;;
    private String address;
    private String city;
    private String province;
    private String country;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;
    @Column(columnDefinition = "TEXT")
    private String content;
}
