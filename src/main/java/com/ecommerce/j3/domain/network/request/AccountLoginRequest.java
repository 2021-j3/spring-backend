package com.ecommerce.j3.domain.network.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AccountLoginRequest {
    private String email;
    private String password;
}
