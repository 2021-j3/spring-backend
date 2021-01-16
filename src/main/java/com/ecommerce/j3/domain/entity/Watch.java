package com.ecommerce.j3.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Watch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long watchId;

    // private Long accountId;
    // Watch : Account ===> N : 1
    @ToString.Exclude()
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    // private Long productId;
    // Watch : Product ===> N : 1
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private LocalDateTime recentWatch;

    private Integer watchCount;

}
