package com.ecommerce.j3.controller.api;


import com.ecommerce.j3.controller.dto.BodyData;
import com.ecommerce.j3.controller.dto.CartDto;
import com.ecommerce.j3.controller.dto.CartItemDto;
import com.ecommerce.j3.domain.J3UserDetails;
import com.ecommerce.j3.service.AccountApiLogicService;
import com.ecommerce.j3.service.CartApiLogicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@Api(tags = {"03. Cart"})
@Slf4j
@RestController
@AllArgsConstructor
public class CartApiController {
    private final AccountApiLogicService accountService;
    private final CartApiLogicService cartService;

    /****************************** Cart APIs *******************************/
    // FIXME: 자신의 카트를 가져오는 api 가 필요합니다
    //  - one to one으로 수정하여 클라이언트에 카트 아이디를 저장하는 방법
    //  - 현재처럼 유저 아이디로 카트를 찾아오는 방법
    //  - 다른 아이디어 ??
    @ApiOperation(value="자신의 카트 읽기", notes="자신의 카트를 읽어온다")
    @GetMapping("/api/cart/my")
    public BodyData<CartDto.CartApiResponse> readMyCart(Authentication authentication){
        // 로그인한 유저를 가져옴
        J3UserDetails userDetails = (J3UserDetails)authentication.getPrincipal();
        Long accountId = userDetails.getAccountId();
        try {
             return BodyData.OK(cartService.findByAccountId(accountId));
        }catch (EntityNotFoundException e){
            cartService.makeCart(accountId);
            BodyData.OK(cartService.findByAccountId(accountId));
        }
        // FIXME: 예외와 에러메시지를 정의해야 합니다
        return BodyData.ERROR("카트가 없는데 생성도 안됩니다");
    }

    /*      20/02/15 Megan
    * PUT method is none of use.
    * DELETE method works for child items recursively which belong to the specific cart.
    */
    @ApiOperation(value = "카트 읽기", notes = "카트를 읽어온다.")
    @GetMapping("/api/carts/{idx}")
    public BodyData<CartDto.CartApiResponse> cartRead(@PathVariable("idx") long cartId) {
        return BodyData.OK(cartService.readCart(cartId));
    }

    @ApiOperation(value = "카트 추가", notes = "카트를 추가한다.")
    @PostMapping("/api/{idx}/carts")
    public BodyData<CartDto.createCartApiResponse> cartCreate(@PathVariable("idx") long accountId) {
        CartDto.createCartApiResponse createCartApiResponse = new CartDto.createCartApiResponse(cartService.makeCart(accountId));
        return BodyData.OK(createCartApiResponse);
    }

    @ApiOperation(value = "카트 삭제", notes = "카트를 삭제한다.")
    @DeleteMapping({"/api/carts/{cartidx}"})
    public BodyData<CartDto.deleteCartApiResponse> cartDelete( @PathVariable("cartidx") long cartidx) {
        cartService.removeCart(cartidx);
        CartDto.deleteCartApiResponse deleteCartApiResponse = new CartDto.deleteCartApiResponse(cartidx);
        return BodyData.OK(deleteCartApiResponse);
    }



    /*************************** Cartitem APIs *****************************/
    /*      20/02/15 Megan
     * GET method is none of use.
     * DELETE method works only for the single item.
     */

    @ApiOperation(value = "카트아이템 추가", notes = "카트아이템을 추가한다.")
    @PostMapping("/api/carts/{cartIdx}/cartItem")
    public BodyData<CartItemDto.createCartItemApiResponse> cartItemCreate(@PathVariable("cartIdx") long cartidx, @RequestBody  CartItemDto.createCartItemApiRequest createCartItemApiRequest) {
        CartItemDto.createCartItemApiResponse createCartItemApiResponse = new CartItemDto.createCartItemApiResponse(cartService.addNewItem(cartidx,createCartItemApiRequest.getProductId(),createCartItemApiRequest.getQuantity()));
        return BodyData.OK(createCartItemApiResponse);
    }
    @ApiOperation(value = "카트아이템 수량 변경 ", notes = "카트아이템 수량 증가/감소시킨다.")
    @PutMapping({"/api/carts/{cartIdx}/cartItem"})
    public BodyData<CartItemDto.createCartItemApiResponse> cartItemUpdate(@PathVariable("cartIdx") long cartidx, @RequestBody CartItemDto.createCartItemApiRequest createCartItemApiRequest) {
        CartItemDto.createCartItemApiResponse createCartItemApiResponse = new CartItemDto.createCartItemApiResponse(this.cartService.addItem(cartidx, createCartItemApiRequest.getProductId(), createCartItemApiRequest.getQuantity()));
        return BodyData.OK(createCartItemApiResponse);
    }
    @ApiOperation(value = "카트아이템 삭제", notes = "카트아이템을 삭제한다.")
    @DeleteMapping("/api/carts/{cartIdx}/cartItem/{cartitemIdx}")
    public BodyData<CartItemDto.deleteCartItemApiResponse> cartItemDelete(@PathVariable("cartIdx") long cartidx,@PathVariable("cartitemIdx") long cartitemidx) {
        CartItemDto.deleteCartItemApiResponse deleteCartItemApiResponse = new CartItemDto.deleteCartItemApiResponse(cartService.deleteItem(cartidx,cartitemidx));
        return BodyData.OK(deleteCartItemApiResponse);
    }
}

