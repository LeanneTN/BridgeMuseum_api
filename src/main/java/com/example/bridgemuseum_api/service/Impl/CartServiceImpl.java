package com.example.bridgemuseum_api.service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.bridgemuseum_api.VO.CartItemVO;
import com.example.bridgemuseum_api.VO.CartVO;
import com.example.bridgemuseum_api.common.CommonResponse;
import com.example.bridgemuseum_api.common.ResponseCode;
import com.example.bridgemuseum_api.domain.Cart;
import com.example.bridgemuseum_api.domain.Product;
import com.example.bridgemuseum_api.mapper.CartMapper;
import com.example.bridgemuseum_api.mapper.ProductMapper;
import com.example.bridgemuseum_api.service.CartService;
import com.example.bridgemuseum_api.utils.BigDecimalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service("cartService")
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public CommonResponse<Cart> addCart(Cart cart) {
        int role = cartMapper.insert(cart);
        if (role == 0){
            return CommonResponse.createForError("add cart failed");
        }
        return CommonResponse.createForSuccess(cart);
    }

    @Override
    public CommonResponse<Cart> updateCartByCart(Cart cart) {
        int role = cartMapper.update(cart, null);
        if (role == 0){
            return CommonResponse.createForError("update cart failed");
        }
        return CommonResponse.createForSuccess(cart);
    }

    @Override
    public CommonResponse<Cart> getCartById(Integer id) {
        Cart cart = cartMapper.selectById(id);
        if(cart == null){
            return CommonResponse.createForError("cart doesn't exist");
        }
        return CommonResponse.createForSuccess(cart);
    }

    @Override
    public CommonResponse<Object> deleteCartById(Integer id) {
        int role = cartMapper.deleteById(id);
        if(role == 0){
            return CommonResponse.createForError("delete cart failed");
        }
        return CommonResponse.createForSuccess();
    }

    @Override
    public CommonResponse<Object> deleteCartByCart(Cart cart) {
        int role = cartMapper.deleteById(cart);
        if(role == 0){
            return CommonResponse.createForError("delete cart failed");
        }
        return CommonResponse.createForSuccess();
    }

    @Override
    public CommonResponse<CartVO> getCartVoByUserId(Long userId) {
        CartVO cartVO = new CartVO();
        List<Cart> cartList = cartMapper.selectList(Wrappers.<Cart>query()
                .eq("user_id", userId));
        List<CartItemVO> cartItemVOList = new ArrayList<>();
        double totalPrice = 0;
        for (Cart cart : cartList){
            CartItemVO cartItemVO = new CartItemVO();
            cartItemVO.setProductId(cart.getProductId());
            cartItemVO.setUserId(userId);
            cartItemVO.setQuantity(cart.getQuantity());
            Product product = productMapper.selectById(cart.getProductId());
            cartItemVO.setProductPrice(product.getPrice());
            cartItemVO.setProductName(product.getName());
            cartItemVOList.add(cartItemVO);
            totalPrice += product.getPrice().doubleValue() * cart.getQuantity();
        }
        if (cartItemVOList.isEmpty()){
            return CommonResponse.createForError(ResponseCode.LIST_EMPTY.getCode(), ResponseCode.LIST_EMPTY.getDescription());
        }
        cartVO.setCartItemVOList(cartItemVOList);
        cartVO.setCartTotalPrice(BigDecimal.valueOf(totalPrice));
        return CommonResponse.createForSuccess(cartVO);
    }

    @Override
    public CommonResponse<CartVO> deleteCartItems(Long userId, Integer productId) {
        int role = cartMapper.delete(Wrappers.<Cart>query().eq("user_id", userId).eq("product_id", productId));
        if (role == 0){
            return CommonResponse.createForError("delete product failed");
        }
        CartVO cartVO = getCartVoByUserId(userId).getData();
        return CommonResponse.createForSuccess(cartVO);
    }

    @Override
    public CommonResponse<Integer> getCartCount(Long userId) {
        List<Cart> cartList = cartMapper.selectList(Wrappers.<Cart>query().eq("user_id", userId));
        int count = cartList.size();
        return CommonResponse.createForSuccess(count);
    }
}
