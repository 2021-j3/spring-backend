package com.ecommerce.j3.controller.api;

import com.ecommerce.j3.domain.entity.Cart;
import com.ecommerce.j3.domain.entity.CartItem;
import com.ecommerce.j3.service.CartService;
import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.expression.ExpressionException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    private CartService cartService;
//    @PostMapping("/")
//    public String CreateCart(Long accountId){
//      생성은 account controller에서 account 생성 후 cart service 사용하여 생성
//    }

    @PutMapping("/")
    public String updateCart(@RequestBody List<Long> cartItemList){
        // 근데 결제 완료시점에서 order item list는 있어도 cart item list는 없겠는데 세션에 저장해서 사용 후 삭제
        return "success";
    }

    @PostMapping("/api/carts/{cartI=_id}/")
    public String insertCartItem(@PathVariable("cart_id") CartItem cartItem, Long productId, HttpSession session){
        Long id = (Long) session.getAttribute("account_id");
        Cart cart = cartService.findOne(cartItem.getCartItemId()).orElseThrow(()->new ExpressionException("잘못된 요청"));
        return "success";
    }
}
