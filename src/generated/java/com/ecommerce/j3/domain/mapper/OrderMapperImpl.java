package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.controller.dto.OrderDto.OrderApiRequest;
import com.ecommerce.j3.controller.dto.OrderDto.OrderApiResponse;
import com.ecommerce.j3.controller.dto.OrderDto.OrderApiResponse.OrderApiResponseBuilder;
import com.ecommerce.j3.domain.entity.Order;
import com.ecommerce.j3.domain.entity.Order.OrderBuilder;
import com.ecommerce.j3.domain.entity.OrderItem;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-02-16T23:36:09+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.7.1.jar, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl extends OrderMapper {

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public Order toEntity(OrderApiRequest dto) {
        if ( dto == null ) {
            return null;
        }

        OrderBuilder order = Order.builder();

        order.account( commonMapper.mapIdToAccount( dto.getAccountId() ) );
        order.orderItems( commonMapper.mapIdsToOrderItems( dto.getOrderItemIds() ) );
        order.ordersId( dto.getOrdersId() );
        order.sessionId( dto.getSessionId() );
        order.token( dto.getToken() );
        order.status( dto.getStatus() );
        order.itemPriceTotal( dto.getItemPriceTotal() );
        order.itemDiscount( dto.getItemDiscount() );
        order.tax( dto.getTax() );
        order.shipping( dto.getShipping() );
        order.userDiscount( dto.getUserDiscount() );
        order.grandTotal( dto.getGrandTotal() );
        order.firstName( dto.getFirstName() );
        order.lastName( dto.getLastName() );
        order.phoneNumber( dto.getPhoneNumber() );
        order.email( dto.getEmail() );
        order.roadAddress( dto.getRoadAddress() );
        order.address( dto.getAddress() );
        order.city( dto.getCity() );
        order.province( dto.getProvince() );
        order.country( dto.getCountry() );
        order.zipCode( dto.getZipCode() );
        order.content( dto.getContent() );
        order.createdAt( dto.getCreatedAt() );
        order.updatedAt( dto.getUpdatedAt() );

        return order.build();
    }

    @Override
    public OrderApiResponse toApiResponse(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderApiResponseBuilder orderApiResponse = OrderApiResponse.builder();

        orderApiResponse.ordersId( order.getOrdersId() );
        orderApiResponse.account( order.getAccount() );
        List<OrderItem> list = order.getOrderItems();
        if ( list != null ) {
            orderApiResponse.orderItems( new ArrayList<OrderItem>( list ) );
        }
        orderApiResponse.sessionId( order.getSessionId() );
        orderApiResponse.token( order.getToken() );
        orderApiResponse.status( order.getStatus() );
        orderApiResponse.itemPriceTotal( order.getItemPriceTotal() );
        orderApiResponse.itemDiscount( order.getItemDiscount() );
        orderApiResponse.tax( order.getTax() );
        orderApiResponse.shipping( order.getShipping() );
        orderApiResponse.userDiscount( order.getUserDiscount() );
        orderApiResponse.grandTotal( order.getGrandTotal() );
        orderApiResponse.firstName( order.getFirstName() );
        orderApiResponse.lastName( order.getLastName() );
        orderApiResponse.phoneNumber( order.getPhoneNumber() );
        orderApiResponse.email( order.getEmail() );
        orderApiResponse.roadAddress( order.getRoadAddress() );
        orderApiResponse.address( order.getAddress() );
        orderApiResponse.city( order.getCity() );
        orderApiResponse.province( order.getProvince() );
        orderApiResponse.country( order.getCountry() );
        orderApiResponse.zipCode( order.getZipCode() );
        orderApiResponse.content( order.getContent() );
        orderApiResponse.createdAt( order.getCreatedAt() );
        orderApiResponse.updatedAt( order.getUpdatedAt() );

        return orderApiResponse.build();
    }
}
