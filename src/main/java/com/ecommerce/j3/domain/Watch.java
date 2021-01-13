package com.ecommerce.j3.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter @Setter
public class Watch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long watchId;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private LocalDateTime recentWatch;
    private Integer watchCount;
}
