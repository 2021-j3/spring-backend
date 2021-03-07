package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.controller.dto.PaymentDto.PaymentApiRequest;
import com.ecommerce.j3.controller.dto.PaymentDto.PaymentApiResponse;
import com.ecommerce.j3.domain.entity.Payment;
import com.ecommerce.j3.domain.entity.Payment.PaymentBuilder;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-07T15:35:00+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.7.1.jar, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class PaymentMapperImpl extends PaymentMapper {

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public Payment toEntity(PaymentApiRequest dto) {
        if ( dto == null ) {
            return null;
        }

        PaymentBuilder payment = Payment.builder();

        payment.account( commonMapper.mapIdToAccount( dto.getAccountId() ) );
        payment.order( commonMapper.mapIdToOrder( dto.getOrderId() ) );
        payment.paymentId( dto.getPaymentId() );
        payment.code( dto.getCode() );
        payment.type( dto.getType() );
        payment.status( dto.getStatus() );
        payment.content( dto.getContent() );
        payment.createdAt( dto.getCreatedAt() );
        payment.updatedAt( dto.getUpdatedAt() );

        return payment.build();
    }

    @Override
    public PaymentApiResponse toApiResponse(Payment entity) {
        if ( entity == null ) {
            return null;
        }

        PaymentApiResponse paymentApiResponse = new PaymentApiResponse();

        paymentApiResponse.setPaymentId( entity.getPaymentId() );
        paymentApiResponse.setAccount( entity.getAccount() );
        paymentApiResponse.setOrder( entity.getOrder() );
        paymentApiResponse.setCode( entity.getCode() );
        paymentApiResponse.setType( entity.getType() );
        paymentApiResponse.setStatus( entity.getStatus() );
        paymentApiResponse.setContent( entity.getContent() );
        paymentApiResponse.setCreatedAt( entity.getCreatedAt() );
        paymentApiResponse.setUpdatedAt( entity.getUpdatedAt() );

        return paymentApiResponse;
    }
}
