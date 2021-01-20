package com.ecommerce.j3.domain.entity;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @UpdateTimestamp
    private LocalDateTime recentWatch;
    private Integer watchCount = 1;
}
