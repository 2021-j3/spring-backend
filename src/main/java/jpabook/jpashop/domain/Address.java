package jpabook.jpashop.domain;

import javax.persistence.*;
@Entity
public class Address {
    @Id
    @GeneratedValue
    private long address_id;

    @ManyToOne
    @JoinColumn(name= "ACCOUNT_ID")
    private Account account;

    @Column(length = 100)
    private String address;
    @Column(length = 100)
    private String road_address;
    @Column(length = 50)
    private String city;
    @Column(length = 50)
    private String province ;
    @Column(length = 50)
    private String country;
}
