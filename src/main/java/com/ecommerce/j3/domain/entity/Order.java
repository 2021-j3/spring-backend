package com.ecommerce.j3.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    // private Long accountId;
    // Order : Account ===> N : 1
    @ToString.Exclude()
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private String sessionId;

    private String token;

    private Integer status;

    private BigDecimal itemPriceTotal;

    private Integer itemDiscount;

    private Float tax;

    private BigDecimal shipping;

    private Float userDiscount;

    private Float grandTotal;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String roadAddress;

    private String address;

    private String city;

    private String province;

    private String country;

    private Integer zipCode;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(columnDefinition = "TEXT")
    private String content;
}
