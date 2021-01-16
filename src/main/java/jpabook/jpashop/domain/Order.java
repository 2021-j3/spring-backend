package jpabook.jpashop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "Orders")
public class Order {
    @Id @GeneratedValue
    private long order_id;

    @ManyToOne
    @JoinColumn(name="ACCOUNT_ID")
    private Account account;

    @Column(length = 100)
    private String session_id;

    @Column(length = 100)
    private String token;
    private Float item_price_total;
    private Float item_discount;
    private Float tax ;
    private Float shipping;
    private Float user_discount;
    private Float grand_total;
    private String first_name;
    private String last_name;
    private String phone_number;
    private String email;
    private String road_address;
    private String address;
    private String city;
    private String province;
    private String country;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String content;
}
