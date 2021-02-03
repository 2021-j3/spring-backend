package com.ecommerce.j3.service;


import com.ecommerce.j3.controller.dto.CartDto;
import com.ecommerce.j3.domain.entity.Cart;
import com.ecommerce.j3.domain.mapper.CartMapper;
import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.repository.CartRepository;
import com.ecommerce.j3.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;  // final 은 entity 를 한번만 declare 하게 해줌.
    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;

    public CartDto.CartApiResponse save(CartDto.CartApiRequest request){
        Cart cart = cartMapper.toEntity(request);
        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }

    public Cart save(Cart cart){
        cartRepository.save(cart);
        return cart;
    }

    public Cart update(Cart cart){
        cartRepository.save(cart);
        return cart;
    }

    public Optional<Cart> findOne(Long cartId){
        return cartRepository.findById(cartId);
    }

    public List<Cart> findAll(){
        return cartRepository.findAll();
    }

    public void remove(Cart cart){
        cartRepository.delete(cart);
    }
}
