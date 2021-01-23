package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.CartItem;
import com.ecommerce.j3.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {
    private CartItemRepository cartItemsRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemsRepository){this.cartItemsRepository = cartItemsRepository;}

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
