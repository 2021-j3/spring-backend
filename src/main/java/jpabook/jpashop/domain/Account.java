package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Account {
    @Id
    @GeneratedValue
    private long account_id;
    private String nickname;
    private String password;
    private String first_name;
    private String last_name;
    @Enumerated(EnumType.STRING)  // ***Do not use EnumType.ORDINAL. It may break down your entire system of web apps.
    private GenderType gender;  // GenderType has 'M' and 'F'.
    private String email;
    private String birthday;
    private String phone_number;
    private LocalDateTime registered_at;
    private LocalDateTime last_login;
    @Enumerated(EnumType.STRING) // AccountType has 'U','S', and 'A'.
    private AccountType account_type;

    @OneToMany(mappedBy= "account", fetch =FetchType.LAZY )  // X to many : LAZY should be set!!
    private List<Address> address = new ArrayList<>();

}
