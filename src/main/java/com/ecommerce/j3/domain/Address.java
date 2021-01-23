package com.ecommerce.j3.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity @Getter @Setter
public class Address {
    @Id
    @GeneratedValue
    private Long addressId;
    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;
    private String address;
    private String road_address;
    private String city;
    private String province;
    private String country;
    private Integer zipCode;

    public static Address createAddress(Account account,String road_address,Integer zipCode){
        Address ad = new Address();
        ad.setAccount(account);
        ad.setRoad_address(road_address);
        ad.setZipCode(zipCode);
        return ad;
    }
}
