package com.ecommerce.j3.controller.dto;


import com.ecommerce.j3.domain.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AddressDto {
    /**
     * 기본 crud request
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddressApiRequest {
        private Long addressId;
        private Long accountId;
        //        private Account account;
        private String address;
        private String roadAddress;
        private String city;
        private String province;
        private String country;
        private Integer zipCode;
    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddressApiResponse {
        private Long addressId;
        private Account account;
        private String address;
        private String roadAddress;
        private String city;
        private String country;
        private Integer zipCode;
    }
}
