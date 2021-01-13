package com.ecommerce.j3.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    private String address;
    private String road_address;
    private String city;
    private String province;
    private String country;
}
