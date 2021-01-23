package com.ecommerce.j3.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

//import javax.validation.constraints.NotEmpty;
@Getter @Setter
public class AccountForm {
    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String nickname;
    private String email;
    private String password ;
    private String address;
  //  private String address;
    private Integer zipcode;
}