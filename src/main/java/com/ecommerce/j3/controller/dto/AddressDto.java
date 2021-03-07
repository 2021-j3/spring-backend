package com.ecommerce.j3.controller.dto;


import com.ecommerce.j3.domain.entity.Account;
import lombok.*;

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
        private String address;
        private String roadAddress;
        private String city;
        private String country;
        private Integer zipCode;
        private boolean thisIsDefault;
    }

    // 기본 배송지를 갱신하기 위해 사용됨
    @Data
    public static class UpdateDefaultAddressRequest {
        private Long addressId;
        private Long accountId;
    }
}
