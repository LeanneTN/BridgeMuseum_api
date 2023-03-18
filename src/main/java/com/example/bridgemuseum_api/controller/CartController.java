package com.example.bridgemuseum_api.controller;

import com.example.bridgemuseum_api.VO.CartVO;
import com.example.bridgemuseum_api.common.CommonResponse;
import com.example.bridgemuseum_api.domain.Cart;
import com.example.bridgemuseum_api.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/id/{id}")
    public CommonResponse<Cart> getCartById(@PathVariable("id") Integer id){
        return cartService.getCartById(id);
    }

    @PutMapping("/")
    public CommonResponse<Cart> addCart(@RequestBody @NotBlank Cart cart){
        return cartService.addCart(cart);
    }

    @PostMapping("/")
    public CommonResponse<Cart> updateCart(@RequestBody @NotBlank Cart cart){
        return cartService.updateCartByCart(cart);
    }

    @DeleteMapping("/id/{id}")
    public CommonResponse<Object> deleteCartById(@PathVariable("id") Integer id){
        return cartService.deleteCartById(id);
    }

    @DeleteMapping("/")
    public CommonResponse<Object> deleteCartByCart(@RequestBody @NotBlank Cart cart){
        return cartService.deleteCartByCart(cart);
    }

    @GetMapping("/cart_vo/user_id/{userId}")
    public CommonResponse<CartVO> getCartVOByUserId(@PathVariable("userId") Long userId){
        return cartService.getCartVoByUserId(userId);
    }

    @DeleteMapping("/cart_vo/user_id/{userId}/items/{productId}")
    public CommonResponse<CartVO> deleteCartItems(@PathVariable("userId") Long userId,
                                                  @PathVariable("productId") Integer productId){
        return cartService.deleteCartItems(userId, productId);
    }

    @GetMapping("/carts/count/user_id/{userId}")
    public CommonResponse<Integer> getCartCount(@PathVariable("userId") Long userId){
        return cartService.getCartCount(userId);
    }
}
