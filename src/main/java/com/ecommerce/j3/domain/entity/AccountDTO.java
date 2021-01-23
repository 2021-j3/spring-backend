package com.ecommerce.j3.domain.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    @NotBlank(message = "닉네임을 입력해주세요")
    private String nickname;
    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "이메일 형식이 아닙니다")
    private String email;
    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;
    private String firstName;
    private String lastName;
    @Column(columnDefinition = "ENUM('MALE','FEMALE')")
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate birthday;
    @PhoneNumber(message="핸드폰번호 형식이 아닙니다")
    private String phoneNumber;
    private AccountType accountType;
}
