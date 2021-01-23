package com.ecommerce.j3.domain.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity @Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
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
    public static Address createAddress(Account account,String road_address,Integer zipCode){
        Address ad = Address.builder()
                .account(account)
                .roadAddress(road_address)
                .zipCode(zipCode)
                .build();
        return ad;
    }
}
