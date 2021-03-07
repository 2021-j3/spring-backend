package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.controller.dto.AccountDto.AccountApiRequest;
import com.ecommerce.j3.controller.dto.AccountDto.AccountApiRequest.AccountApiRequestBuilder;
import com.ecommerce.j3.controller.dto.AccountDto.AccountApiResponse;
import com.ecommerce.j3.controller.dto.AccountDto.AccountApiResponse.AccountApiResponseBuilder;
import com.ecommerce.j3.controller.dto.AccountDto.CreateAccountRequest;
import com.ecommerce.j3.controller.dto.AccountDto.CreateAccountResponse;
import com.ecommerce.j3.controller.dto.AccountDto.UpdateAccountRequest;
import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.Account.AccountBuilder;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-08T00:33:04+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.7.1.jar, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class AccountMapperImpl extends AccountMapper {

    @Override
    public Account toEntity(AccountApiRequest dto) {
        if ( dto == null ) {
            return null;
        }

        AccountBuilder account = Account.builder();

        account.passwordHash( dto.getPassword() );
        account.accountId( dto.getAccountId() );
        account.email( dto.getEmail() );
        account.firstName( dto.getFirstName() );
        account.lastName( dto.getLastName() );
        account.birthday( dto.getBirthday() );
        account.gender( dto.getGender() );
        account.phoneNumber( dto.getPhoneNumber() );
        account.accountType( dto.getAccountType() );
        account.registeredAt( dto.getRegisteredAt() );
        account.lastLogin( dto.getLastLogin() );
        account.defaultAddress( dto.getDefaultAddress() );

        return account.build();
    }

    @Override
    public AccountApiResponse toApiResponse(Account entity) {
        if ( entity == null ) {
            return null;
        }

        AccountApiResponseBuilder accountApiResponse = AccountApiResponse.builder();

        accountApiResponse.email( entity.getEmail() );
        accountApiResponse.firstName( entity.getFirstName() );
        accountApiResponse.lastName( entity.getLastName() );
        accountApiResponse.accountType( entity.getAccountType() );
        accountApiResponse.defaultAddress( entity.getDefaultAddress() );

        return accountApiResponse.build();
    }

    @Override
    public AccountApiRequest toRequestDto(UpdateAccountRequest dtoWithSomeField) {
        if ( dtoWithSomeField == null ) {
            return null;
        }

        AccountApiRequestBuilder accountApiRequest = AccountApiRequest.builder();

        accountApiRequest.accountId( dtoWithSomeField.getAccountId() );
        accountApiRequest.email( dtoWithSomeField.getEmail() );
        accountApiRequest.password( dtoWithSomeField.getPassword() );
        accountApiRequest.gender( dtoWithSomeField.getGender() );
        accountApiRequest.birthday( dtoWithSomeField.getBirthday() );
        accountApiRequest.phoneNumber( dtoWithSomeField.getPhoneNumber() );

        return accountApiRequest.build();
    }

    @Override
    public AccountApiRequest toRequestDto(CreateAccountRequest dtoWithSomeField) {
        if ( dtoWithSomeField == null ) {
            return null;
        }

        AccountApiRequestBuilder accountApiRequest = AccountApiRequest.builder();

        accountApiRequest.email( dtoWithSomeField.getEmail() );
        accountApiRequest.password( dtoWithSomeField.getPassword() );
        accountApiRequest.gender( dtoWithSomeField.getGender() );

        return accountApiRequest.build();
    }

    @Override
    public CreateAccountResponse toCreateAccountResponse(Account account) {
        if ( account == null ) {
            return null;
        }

        String firstName = null;
        String lastName = null;

        firstName = account.getFirstName();
        lastName = account.getLastName();

        Long id = null;
        LocalDateTime dateTime = null;

        CreateAccountResponse createAccountResponse = new CreateAccountResponse( id, dateTime, firstName, lastName );

        return createAccountResponse;
    }
}
