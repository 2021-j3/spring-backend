package com.ecommerce.j3.domain.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
//@Table ( schema = "SHOP")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private String address;
    private String roadAddress;
    private String city;

    private String province;

    private String country;

    private Integer zipCode;
}
