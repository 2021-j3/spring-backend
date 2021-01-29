package com.ecommerce.j3.domain.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    private String email;
    private String passwordHash;
    private String firstName;
    private String lastName;

    @Column(columnDefinition = "ENUM('MALE','FEMALE')")
    @Enumerated(EnumType.STRING)
    private GenderType gender;

    @Column(columnDefinition = "VARCHAR")
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private LocalDate birthday;

    private String phoneNumber;

    @CreationTimestamp
    private LocalDateTime registeredAt;

    private LocalDateTime lastLogin;

    @Column(columnDefinition = "ENUM('USER','SELLER','ADMIN')")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
//    @OneToMany
//    @JoinColumn(name="account_id")
//    private List<Watch> watchList;
}
