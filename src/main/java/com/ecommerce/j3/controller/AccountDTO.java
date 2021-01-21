package com.ecommerce.j3.controller;

import com.ecommerce.j3.domain.Account;
import com.ecommerce.j3.domain.GenderType;
import com.ecommerce.j3.domain.PhoneNumber;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

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
    @NotBlank(message = "성을 입력해주세요")
    private String firstName;
    @NotBlank(message = "이름을 입력해주세요")
    private String lastName;
    @Column(columnDefinition = "ENUM('MALE','FEMALE')")
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDateTime birthday;
    @NotBlank(message = "핸드폰번호를 입력해주세요")
    @PhoneNumber(message="핸드폰번호 형식이 아닙니다")
    private String phoneNumber;


    public Account toEntity(){
        // 필수 값
        Account account = new Account();
        account.setNickname(nickname);
        account.setEmail(email);
        account.setPasswordHash(password);
        account.setPhoneNumber(phoneNumber);

        // 선택
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setGender(gender);
        account.setBirthday(birthday);
        return account;
    }

    public AccountDTO(Account account){
        this.nickname = account.getNickname();
        this.email = account.getNickname();
        this.password = account.getPasswordHash();
        this.firstName = account.getFirstName();
        this.lastName = account.getLastName();
        this.gender = account.getGender();
        this.birthday = account.getBirthday();
        this.phoneNumber = account.getPhoneNumber();
    }
}
