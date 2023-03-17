package com.example.bridgemuseum_api.service;

import com.example.bridgemuseum_api.VO.CartVO;
import com.example.bridgemuseum_api.common.CommonResponse;
import com.example.bridgemuseum_api.domain.Cart;

public interface CartService {
    public CommonResponse<Cart> addCart(Cart cart);

    public CommonResponse<Cart> updateCartByCart(Cart cart);

    public CommonResponse<Cart> getCartById(Integer id);

    public CommonResponse<Object> deleteCartById(Integer id);

    public CommonResponse<Object> deleteCartByCart(Cart cart);

    public CommonResponse<CartVO> getCartVoByUserId(Long userId);

    public CommonResponse<CartVO> deleteCartItems(Long userId, Integer productId);

    public CommonResponse<Integer> getCartCount(Long userId);
}
