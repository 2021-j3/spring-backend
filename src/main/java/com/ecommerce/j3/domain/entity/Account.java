package com.ecommerce.j3.domain.entity;

import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table (name = "ACCOUNT", schema = "SHOP")

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @NotNull
    private String email;

    @NotNull
    private String passwordHash;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

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
    @JoinTable(
            name = "default_address",
            joinColumns = @JoinColumn( name = "account_id"),
            inverseJoinColumns = @JoinColumn( name = "address_id")
    )
    private Address default_address;
    @OneToMany(mappedBy = "account")
    List<Address> addresses;
}
