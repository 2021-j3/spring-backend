package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.CartDto;
import com.ecommerce.j3.controller.dto.CartItemDto;
import com.ecommerce.j3.domain.entity.Cart;
import com.ecommerce.j3.domain.entity.CartItem;
import com.ecommerce.j3.domain.mapper.CartItemMapper;
import com.ecommerce.j3.domain.mapper.CartMapper;
import com.ecommerce.j3.repository.CartItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemsRepository;
    private final CartItemMapper cartItemMapper;

    public CartItemDto.CartItemApiResponse save(CartItemDto.CartItemApiRequest request){
        CartItem cartItem = cartItemMapper.toEntity(request);
        cartItemsRepository.save(cartItem);
        return cartItemMapper.toDto(cartItem);
    }
    public CartItem save(CartItem cartItems){
        cartItemsRepository.save(cartItems);
        return cartItems;
    }

    public CartItem update(CartItem cartItems){
        cartItemsRepository.save(cartItems);
        return cartItems;
    }

    public Optional<CartItem> findOne(Long cartItemId){
        return cartItemsRepository.findById(cartItemId);
    }

    public List<CartItem> findAll(){
        return cartItemsRepository.findAll();
    }

    public void remove(CartItem cartItems){
        cartItemsRepository.delete(cartItems);
    }
}
