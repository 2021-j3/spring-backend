package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.controller.dto.CartDto.CartApiRequest;
import com.ecommerce.j3.controller.dto.CartDto.CartApiResponse;
import com.ecommerce.j3.domain.entity.Cart;
import com.ecommerce.j3.domain.entity.Cart.CartBuilder;
import com.ecommerce.j3.domain.entity.CartItem;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-08T14:51:45+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.7.1.jar, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class CartMapperImpl extends CartMapper {

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public Cart toEntity(CartApiRequest dto) {
        if ( dto == null ) {
            return null;
        }

        CartBuilder cart = Cart.builder();

        cart.account( commonMapper.mapIdToAccount( dto.getAccountId() ) );
        cart.cartItems( commonMapper.mapIdsToCartItems( dto.getCartItemIds() ) );
        cart.cartId( dto.getCartId() );
        cart.sessionId( dto.getSessionId() );
        cart.token( dto.getToken() );
        cart.status( dto.getStatus() );
        cart.itemPriceTotal( dto.getItemPriceTotal() );
        cart.itemDiscount( dto.getItemDiscount() );
        cart.tax( dto.getTax() );
        cart.shipping( dto.getShipping() );
        cart.userDiscount( dto.getUserDiscount() );
        cart.grandTotal( dto.getGrandTotal() );
        cart.roadAddress( dto.getRoadAddress() );
        cart.address( dto.getAddress() );
        cart.city( dto.getCity() );
        cart.province( dto.getProvince() );
        cart.country( dto.getCountry() );
        cart.zipCode( dto.getZipCode() );
        cart.content( dto.getContent() );
        cart.createdAt( dto.getCreatedAt() );
        cart.updatedAt( dto.getUpdatedAt() );

        return cart.build();
    }

    @Override
    public CartApiResponse toApiResponse(Cart entity) {
        if ( entity == null ) {
            return null;
        }

        CartApiResponse cartApiResponse = new CartApiResponse();

        cartApiResponse.setCartId( entity.getCartId() );
        cartApiResponse.setAccount( entity.getAccount() );
        List<CartItem> list = entity.getCartItems();
        if ( list != null ) {
            cartApiResponse.setCartItems( new ArrayList<CartItem>( list ) );
        }
        cartApiResponse.setSessionId( entity.getSessionId() );
        cartApiResponse.setToken( entity.getToken() );
        cartApiResponse.setStatus( entity.getStatus() );
        cartApiResponse.setItemPriceTotal( entity.getItemPriceTotal() );
        cartApiResponse.setItemDiscount( entity.getItemDiscount() );
        cartApiResponse.setTax( entity.getTax() );
        cartApiResponse.setShipping( entity.getShipping() );
        cartApiResponse.setUserDiscount( entity.getUserDiscount() );
        cartApiResponse.setGrandTotal( entity.getGrandTotal() );
        cartApiResponse.setRoadAddress( entity.getRoadAddress() );
        cartApiResponse.setAddress( entity.getAddress() );
        cartApiResponse.setCity( entity.getCity() );
        cartApiResponse.setProvince( entity.getProvince() );
        cartApiResponse.setCountry( entity.getCountry() );
        cartApiResponse.setZipCode( entity.getZipCode() );
        cartApiResponse.setContent( entity.getContent() );
        cartApiResponse.setCreatedAt( entity.getCreatedAt() );
        cartApiResponse.setUpdatedAt( entity.getUpdatedAt() );

        return cartApiResponse;
    }
}
