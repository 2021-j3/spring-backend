package com.ecommerce.j3.domain;

import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.AccountType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class J3UserDetails extends User {

    private Long id;

    public J3UserDetails(Account account){
        super(account.getEmail(),
                account.getPasswordHash(),
                authorities(account.getAccountType()));
        this.id = account.getAccountId();
    }

    /**
     * Account의 유저타입을 spring security 에서 권한 이름으로 요구하는 ROLE_~ 로 변환하는 메소드
     * @param accountType { AccountType }
     * @return authorities { GrantedAuthority }
     */
    private static Collection<GrantedAuthority> authorities(AccountType accountType) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + accountType.toString()));
        return authorities;
    }
}
