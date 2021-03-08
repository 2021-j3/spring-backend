package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.controller.dto.AddressDto.AddressApiRequest;
import com.ecommerce.j3.controller.dto.AddressDto.AddressApiResponse;
import com.ecommerce.j3.domain.entity.Address;
import com.ecommerce.j3.domain.entity.Address.AddressBuilder;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-08T16:12:17+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.7.1.jar, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class AddressMapperImpl extends AddressMapper {

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public Address toEntity(AddressApiRequest addressRequest) {
        if ( addressRequest == null ) {
            return null;
        }

        AddressBuilder address = Address.builder();

        address.account( commonMapper.mapIdToAccount( addressRequest.getAccountId() ) );
        address.addressId( addressRequest.getAddressId() );
        address.address( addressRequest.getAddress() );
        address.roadAddress( addressRequest.getRoadAddress() );
        address.city( addressRequest.getCity() );
        address.province( addressRequest.getProvince() );
        address.country( addressRequest.getCountry() );
        address.zipCode( addressRequest.getZipCode() );

        return address.build();
    }

    @Override
    public AddressApiResponse toApiResponse(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressApiResponse addressApiResponse = new AddressApiResponse();

        addressApiResponse.setAddressId( address.getAddressId() );
        addressApiResponse.setAddress( address.getAddress() );
        addressApiResponse.setRoadAddress( address.getRoadAddress() );
        addressApiResponse.setCity( address.getCity() );
        addressApiResponse.setCountry( address.getCountry() );
        addressApiResponse.setZipCode( address.getZipCode() );

        return addressApiResponse;
    }
}
