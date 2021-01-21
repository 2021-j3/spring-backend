package com.ecommerce.j3.domain.network.response;

import com.ecommerce.j3.domain.entity.AccountType;
import com.ecommerce.j3.domain.entity.GenderType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountApiResponse {
    private Long accountId;

    private String email;

    // 암호화 처리
    private String passwordHash;

    private String firstName;
    private String lastName;

    private GenderType gender;

    private LocalDate birthday;

    private String phoneNumber;

    @CreationTimestamp
    private LocalDateTime registeredAt;

    private LocalDateTime lastLogin;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;
}
