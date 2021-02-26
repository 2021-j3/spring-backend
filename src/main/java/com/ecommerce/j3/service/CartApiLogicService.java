package com.ecommerce.j3.service;


import com.ecommerce.j3.controller.dto.CartDto;
import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.Cart;
import com.ecommerce.j3.domain.entity.CartItem;
import com.ecommerce.j3.domain.mapper.CartMapper;
import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.repository.CartItemRepository;
import com.ecommerce.j3.repository.CartRepository;
import com.ecommerce.j3.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartApiLogicService {
    private final CartRepository cartRepository;  // final 은 entity 를 한번만 declare 하게 해줌.
    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final CartMapper cartMapper;

    public CartDto.CartApiResponse save(CartDto.CartApiRequest request){
        Cart cart = cartMapper.toEntity(request);
        cartRepository.save(cart);
        return cartMapper.toApiResponse(cart);
    }

    public Cart save(Cart cart){
        cartRepository.save(cart);
        return cart;
    }

    public Cart update(Cart cart){
        cartRepository.save(cart);
        return cart;
    }

    public Cart findById(Long cartId){
        return cartRepository.findById(cartId).get();
    }

    public CartDto.CartApiResponse readCart(Long id){
        // 1. id -> repository getOne / getById
        Cart cartFromDB = findById(id);
        // 2. return cart -> cartApiResponse
        return cartMapper.toApiResponse(cartFromDB);
    }

    public List<Cart> findAll(){
        return cartRepository.findAll();
    }  // Might be deleted soon. No use of it.

    public void removeCart(Long cartId){
        cartRepository.delete(findById(cartId));
    }

    public long makeCart(long accountId) {

        return cartRepository.save(Cart.createCart(accountRepository.findById(accountId).get())).getCartId();

    }

    public long addItem(long cartid,long productid,Integer quantity) {
        Cart cart = findById(cartid);
        cart.getCartItems().add(CartItem.createCartItem(cart,quantity,productRepository.findById(productid).orElse(null)));

        return save(cart).getCartId();

    }

    public long deleteItem(long cartId, long cartitemId) {
        Cart cart = findById(cartId);
        cart.getCartItems().remove(cartItemRepository.findById(cartitemId).get());
        return save(cart).getCartId();
    }
}
