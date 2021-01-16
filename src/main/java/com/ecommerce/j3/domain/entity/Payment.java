package com.ecommerce.j3.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    // private Long accountId;
    // Transaction : Account ===> N : 1
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    // private Long ordersId;
    // Transaction : Orders ===> N : 1
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private String code;

    private Integer type;

    private Integer status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(columnDefinition = "TEXT")
    private String content;
}
