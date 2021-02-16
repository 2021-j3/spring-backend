package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.controller.dto.CartItemDto.CartItemApiRequest;
import com.ecommerce.j3.controller.dto.CartItemDto.CartItemApiResponse;
import com.ecommerce.j3.domain.entity.CartItem;
import com.ecommerce.j3.domain.entity.CartItem.CartItemBuilder;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-02-16T23:36:09+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.7.1.jar, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class CartItemMapperImpl extends CartItemMapper {

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public CartItem toEntity(CartItemApiRequest dto) {
        if ( dto == null ) {
            return null;
        }

        CartItemBuilder cartItem = CartItem.builder();

        cartItem.product( commonMapper.mapIdToProduct( dto.getProductId() ) );
        cartItem.cart( commonMapper.mapIdToCart( dto.getCartId() ) );
        cartItem.cartItemId( dto.getCartItemId() );
        cartItem.sku( dto.getSku() );
        cartItem.price( dto.getPrice() );
        cartItem.discountRate( dto.getDiscountRate() );
        cartItem.quantity( dto.getQuantity() );
        cartItem.active( dto.getActive() );
        cartItem.content( dto.getContent() );
        cartItem.createdAt( dto.getCreatedAt() );
        cartItem.updatedAt( dto.getUpdatedAt() );

        return cartItem.build();
    }

    @Override
    public CartItemApiResponse toApiResponse(CartItem cart) {
        if ( cart == null ) {
            return null;
        }

        CartItemApiResponse cartItemApiResponse = new CartItemApiResponse();

        cartItemApiResponse.setCartItemId( cart.getCartItemId() );
        cartItemApiResponse.setProduct( cart.getProduct() );
        cartItemApiResponse.setCart( cart.getCart() );
        cartItemApiResponse.setSku( cart.getSku() );
        cartItemApiResponse.setPrice( cart.getPrice() );
        cartItemApiResponse.setDiscountRate( cart.getDiscountRate() );
        cartItemApiResponse.setQuantity( cart.getQuantity() );
        cartItemApiResponse.setActive( cart.getActive() );
        cartItemApiResponse.setContent( cart.getContent() );
        cartItemApiResponse.setCreatedAt( cart.getCreatedAt() );
        cartItemApiResponse.setUpdatedAt( cart.getUpdatedAt() );

        return cartItemApiResponse;
    }
}