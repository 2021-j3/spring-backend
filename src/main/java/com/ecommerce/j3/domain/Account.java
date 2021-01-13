package com.ecommerce.j3.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private String nickname;
    private String email;
    private String passwordHash;
    private String firstName;
    private String lastName;
    @Column(columnDefinition = "ENUM('MALE','FEMALE')")
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    @Column(columnDefinition = "VARCHAR")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime birthday;
    private String phoneNumber;
    @CreationTimestamp
    private LocalDateTime  registeredAt;
    private LocalDateTime  lastLogin;
    @Column(columnDefinition = "ENUM('USER','SELLER','ADMIN')")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
//    @OneToMany
//    @JoinColumn(name="account_id")
//    private List<Watch> watchList;
}
