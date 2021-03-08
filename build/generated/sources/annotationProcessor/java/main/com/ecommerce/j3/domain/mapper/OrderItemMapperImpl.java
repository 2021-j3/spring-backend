package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.controller.dto.OrderItemDto.OrderItemApiRequest;
import com.ecommerce.j3.controller.dto.OrderItemDto.OrderItemApiResponse;
import com.ecommerce.j3.domain.entity.OrderItem;
import com.ecommerce.j3.domain.entity.OrderItem.OrderItemBuilder;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-08T16:12:17+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.7.1.jar, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class OrderItemMapperImpl extends OrderItemMapper {

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public OrderItem toEntity(OrderItemApiRequest dto) {
        if ( dto == null ) {
            return null;
        }

        OrderItemBuilder orderItem = OrderItem.builder();

        orderItem.product( commonMapper.mapIdToProduct( dto.getProductId() ) );
        orderItem.order( commonMapper.mapIdToOrder( dto.getOrderId() ) );
        orderItem.orderItemId( dto.getOrderItemId() );
        orderItem.sku( dto.getSku() );
        orderItem.price( dto.getPrice() );
        orderItem.discountPrice( dto.getDiscountPrice() );
        orderItem.quantity( dto.getQuantity() );
        orderItem.content( dto.getContent() );
        orderItem.createdAt( dto.getCreatedAt() );
        orderItem.updatedAt( dto.getUpdatedAt() );

        return orderItem.build();
    }

    @Override
    public OrderItemApiResponse toApiResponse(OrderItem cart) {
        if ( cart == null ) {
            return null;
        }

        OrderItemApiResponse orderItemApiResponse = new OrderItemApiResponse();

        orderItemApiResponse.setOrderItemId( cart.getOrderItemId() );
        orderItemApiResponse.setProduct( cart.getProduct() );
        orderItemApiResponse.setOrder( cart.getOrder() );
        orderItemApiResponse.setSku( cart.getSku() );
        orderItemApiResponse.setPrice( cart.getPrice() );
        orderItemApiResponse.setDiscountPrice( cart.getDiscountPrice() );
        orderItemApiResponse.setQuantity( cart.getQuantity() );
        orderItemApiResponse.setContent( cart.getContent() );
        orderItemApiResponse.setCreatedAt( cart.getCreatedAt() );
        orderItemApiResponse.setUpdatedAt( cart.getUpdatedAt() );

        return orderItemApiResponse;
    }
}
