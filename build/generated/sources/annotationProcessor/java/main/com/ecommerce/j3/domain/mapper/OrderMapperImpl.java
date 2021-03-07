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
    date = "2021-03-07T15:35:00+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.7.1.jar, environment: Java 11.0.9 (Oracle Corporation)"
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
        orderApiResponse.content( order.getContent() );
        orderApiResponse.createdAt( order.getCreatedAt() );
        orderApiResponse.updatedAt( order.getUpdatedAt() );

        return orderApiResponse.build();
    }
}
