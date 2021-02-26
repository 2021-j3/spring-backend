package com.ecommerce.j3.controller.dto;

import com.ecommerce.j3.domain.J3UserDetails;
import com.ecommerce.j3.domain.entity.AccountType;
import com.ecommerce.j3.domain.entity.Address;
import com.ecommerce.j3.domain.entity.GenderType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

public class AccountDto {
    /**
     * 기본 crud request
     */
    @Getter
    @Builder
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AccountApiRequest {
        private Long accountId;

        private String email;
        private String password;
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

        private Address defaultAddress;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AccountApiResponse {

//    private Long accountId;

        private String email;

        private String firstName;
        private String lastName;
//
//    private GenderType gender;
//
//    private LocalDate birthday;
//
//    private String phoneNumber;
//
//    @CreationTimestamp
//    private LocalDateTime registeredAt;

//        private LocalDateTime lastLogin;

        @Enumerated(EnumType.STRING)
        private AccountType accountType;

        private Address defaultAddress;
    }

    /**
     * 2021-02-15 penguin418
     * 로그인 정용으로 사용
     **/
    @Data
    public static class LoginRequest {
        private String email;
        private String password;
    }

    @Data
    @AllArgsConstructor
    public static class AccountLoginResponse {
        private String username;
        private Collection<? extends GrantedAuthority> authorities;
        private String token;
    }

    @Data
    public static class ReadAccountRequest {
        private String email;
    }

    @Data
    public static class CreateAccountRequest {
        private String email;
        private String password;
        private String lastname;
        private String firstname;
        private GenderType gender;
        private AccountType accounttype;
    }

    @Data
    public static class UpdateAccountRequest {
        private String email;
        private String lastname;
        private String firstname;
    }

    @Data
    public static class CreateAccountResponse {

        private Long id;
        private LocalDateTime dateTime;
        private String firstName;
        private String lastName;

        public CreateAccountResponse(Long id, LocalDateTime dateTime, String firstName, String lastName) {
            this.id = id;
            this.dateTime = dateTime;
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }
}
