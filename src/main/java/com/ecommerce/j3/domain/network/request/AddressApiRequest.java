package com.ecommerce.j3.domain.network.request;
import com.ecommerce.j3.domain.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressApiRequest {
    private Long addressId;
    private Account account;
    private String address;
    private String roadAddress;
    private String city;
    private String country;
    private Integer zipCode;
}
