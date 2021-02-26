package com.ecommerce.j3.controller.api;


import com.ecommerce.j3.controller.dto.BodyData;
import com.ecommerce.j3.controller.dto.CartDto;
import com.ecommerce.j3.controller.dto.CartItemDto;
import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.service.CartApiLogicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"03. Cart"})
@Slf4j
@RestController
@AllArgsConstructor
public class CartApiController {
    private final CartApiLogicService cartService;

    /****************************** Cart APIs *******************************/
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
        CartItemDto.createCartItemApiResponse createCartItemApiResponse = new CartItemDto.createCartItemApiResponse(cartService.addItem(cartidx,createCartItemApiRequest.getProductid(),createCartItemApiRequest.getQuantity()));
        return BodyData.OK(createCartItemApiResponse);
    }

    @ApiOperation(value = "카트아이템 삭제", notes = "카트아이템을 삭제한다.")
    @DeleteMapping("/api/carts/{cartIdx}/cartItem/{cartitemIdx}")
    public BodyData<CartItemDto.deleteCartItemApiResponse> cartItemDelete(@PathVariable("cartIdx") long cartidx,@PathVariable("cartitemIdx") long cartitemidx) {
        CartItemDto.deleteCartItemApiResponse deleteCartItemApiResponse = new CartItemDto.deleteCartItemApiResponse(cartService.deleteItem(cartidx,cartitemidx));
        return BodyData.OK(deleteCartItemApiResponse);
    }
}

