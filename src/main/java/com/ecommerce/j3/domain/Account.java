package com.ecommerce.j3.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Account {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String email;
    @Column(name = "password_hash")
    private String passwordHash;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "gender", columnDefinition = "ENUM('MALE','FEMALE')")
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    @Column(columnDefinition = "VARCHAR")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "registered_at")
    @CreationTimestamp
    private Date registeredAt;
    @Column(name = "last_login")
    private Date lastLogin;
    @Column(name = "account_type", columnDefinition = "ENUM('USER','SELLER','ADMIN')")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
//    @OneToMany
//    @JoinColumn(name="account_id")
//    private List<Watch> watchList;
}
