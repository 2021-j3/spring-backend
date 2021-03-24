package com.ecommerce.j3.domain.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private String email;

    @NotNull
    private String passwordHash;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Column(columnDefinition = "VARCHAR")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @NotNull
    @Column(columnDefinition = "enum('MALE', 'FEMALE')")
    @Enumerated(EnumType.STRING)
    private GenderType gender;

    private String phoneNumber;

    @NotNull
    @Column(columnDefinition = "enum('USER', 'SELLER', 'ADMIN')")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @NotNull
    @CreationTimestamp
    private LocalDateTime registeredAt;

    private LocalDateTime lastLogin;

    @OneToOne
    @JoinTable(schema = "shop",
            name = "default_address",
            joinColumns = @JoinColumn( name = "account_id"),
            inverseJoinColumns = @JoinColumn( name = "address_id")
    )
    private Address defaultAddress;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    List<Address> addresses;

    public void addAddress(Address address){
        if(address.equals(null)){
           addresses = new ArrayList<>();
        }
        addresses.add(address);
    }

//    자신의 카트를 가져옴
//    @OneToOne(mappedBy = "account")
//    private Cart cart;
}
