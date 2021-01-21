package com.ecommerce.j3.domain.network.request;

import com.ecommerce.j3.domain.entity.AccountType;
import com.ecommerce.j3.domain.entity.GenderType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountApiRequest {
    private Long accountId;

    private String email;
    private String passwordHash;
    private String firstName;
    private String lastName;

    @Column(columnDefinition = "ENUM('MALE','FEMALE')")
    @Enumerated(EnumType.STRING)
    private GenderType gender;

    @Column(columnDefinition = "VARCHAR")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    private String phoneNumber;

    @CreationTimestamp
    private LocalDateTime registeredAt;

    private LocalDateTime lastLogin;

    @Column(columnDefinition = "ENUM('USER','SELLER','ADMIN')")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

}
