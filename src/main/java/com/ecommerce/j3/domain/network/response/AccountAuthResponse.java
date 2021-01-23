package com.ecommerce.j3.domain.network.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountAuthResponse {
    private String username;
    private Collection<? extends GrantedAuthority> authorities;
    private String sessionId;
}
