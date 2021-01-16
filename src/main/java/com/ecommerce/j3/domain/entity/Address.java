package com.ecommerce.j3.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    // private Long accountId;
    // Address : Account ===> N : 1
    @ToString.Exclude()
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
