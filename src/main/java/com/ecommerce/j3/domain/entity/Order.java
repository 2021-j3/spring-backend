package com.ecommerce.j3.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "orders")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String sessionId;
    private String token;
    private short status;
    private BigDecimal itemPriceTotal;
    private float itemDiscount;
    private float tax;
    private BigDecimal shipping;
    private float userDiscount;
    private float grandTotal;
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
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @Column(columnDefinition = "TEXT")
    private String content;
}
