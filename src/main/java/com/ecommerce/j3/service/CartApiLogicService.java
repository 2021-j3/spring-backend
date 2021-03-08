package com.ecommerce.j3.service;


import com.ecommerce.j3.controller.dto.CartDto;
import com.ecommerce.j3.domain.entity.*;
import com.ecommerce.j3.domain.mapper.CartMapper;
import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.repository.CartItemRepository;
import com.ecommerce.j3.repository.CartRepository;
import com.ecommerce.j3.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
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
        // FIXME:  카트 생성이 안됩니다
        return cartRepository.save(Cart.createCart(accountRepository.findById(accountId).get())).getCartId();

    }

    AccountApiLogicService accountApiLogicService;
    @Transactional
    public CartDto.CartApiResponse findByAccountId(Long accountId){
        Cart cart = cartRepository.findByAccountId(accountId).orElseThrow(EntityNotFoundException::new);
        return cartMapper.toApiResponse(cart);
    }

    // add new item
    public long addNewItem(long cartid, long productid, Integer quantity) {
        Cart cart = this.findById(cartid);
        cart.getCartItems().add(CartItem.createCartItem(cart, quantity,this.productRepository.findById(productid).orElse(null)));
        return this.save(cart).getCartId();
    }
    // update the quantity existing item
    public long addItem(long cartId, long productId, Integer quantity) {
        Cart cart = this.findById(cartId);
        CartItem cartItem = new CartItem();

        for(CartItem ci : cart.getCartItems()) {
            if (ci.getProduct().getProductId() == productId) {
                cartItem = ci;
                break;
            }
        }

        cart.updateItem(cartItem.getPrice(), quantity, cartItem.getDiscountPrice());
        if (cartItem.UpdateQuantity(quantity) == 0) {
            cart.getCartItems().remove(cartItem);
        }

        return this.save(cart).getCartId();
    }
    public long deleteItem(long cartId, long cartitemId) {
        Cart cart = this.findById(cartId);
        CartItem cartItem = (CartItem)this.cartItemRepository.findById(cartitemId).get();
        cartItem.setActive('D');
        cart.updateItem(cartItem);
        cart.getCartItems().remove(cartItem);
        return this.save(cart).getCartId();
    }
}
