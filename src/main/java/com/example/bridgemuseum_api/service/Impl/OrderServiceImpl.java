package com.example.bridgemuseum_api.service.Impl;

import com.example.bridgemuseum_api.VO.*;
import com.example.bridgemuseum_api.common.CONSTANT;
import com.example.bridgemuseum_api.common.CommonResponse;
import com.example.bridgemuseum_api.common.ResponseCode;
import com.example.bridgemuseum_api.domain.Cart;
import com.example.bridgemuseum_api.domain.Order;
import com.example.bridgemuseum_api.domain.OrderProduct;
import com.example.bridgemuseum_api.domain.Product;
import com.example.bridgemuseum_api.mapper.CartMapper;
import com.example.bridgemuseum_api.mapper.OrderMapper;
import com.example.bridgemuseum_api.mapper.OrderProductMapper;
import com.example.bridgemuseum_api.mapper.ProductMapper;
import com.example.bridgemuseum_api.service.CartService;
import com.example.bridgemuseum_api.service.OrderService;
import com.example.bridgemuseum_api.utils.BigDecimalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private OrderProductMapper orderProductMapper;

    @Override
    public CommonResponse<OrderVO> addOrder(Long userId, OrderAddress address) {
        Order order = new Order();
        order.setOrderNo(generateOrderNo());
        order.setCreateTime(LocalDateTime.now());
        List<Cart> cartList = cartService.getCheckedCartItems(userId).getData();
        if (cartList.isEmpty()){
            return CommonResponse.createForError(ResponseCode.LIST_EMPTY.getCode(), "no checked item in the cart");
        }
        OrderVO orderVO = new OrderVO();
        List<OrderItemVO> orderItemVOList = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Cart cart : cartList){
            OrderItemVO orderItemVO = new OrderItemVO();
            OrderProduct orderProduct = new OrderProduct();
            Product product = productMapper.selectById(cart.getProductId());
            orderItemVO.setQuantity(cart.getQuantity());
            orderItemVO.setProductName(product.getName());
            orderItemVO.setCurrentPrice(product.getPrice());
            orderItemVO.setTotalPrice(BigDecimal.valueOf(cart.getTotalPriceOfProduct()));
            orderItemVOList.add(orderItemVO);
            totalPrice = BigDecimalUtil.add(totalPrice.doubleValue(), BigDecimal.valueOf(cart.getTotalPriceOfProduct()).doubleValue());
            orderProduct.setOrderNo(order.getOrderNo().toString());
            orderProduct.setProductPrice(product.getPrice());
            orderProduct.setProductName(product.getName());
            orderProduct.setProductQuantity(cart.getQuantity());
            int role = orderProductMapper.insert(orderProduct);
            if (role == 0){
                return CommonResponse.createForError("create order failed");
            }
        }
        order.setOrderStatus(CONSTANT.ORDER_STATUS.OPEN);
        order.setCity(address.getCity());
        order.setProvince(address.getProvince());
        order.setCountry(address.getCountry());
        order.setPreciseAddress(address.getPreciseAddress());
        order.setPaymentPrice(totalPrice);
        int role = orderMapper.insert(order);
        if(role == 0){
            return CommonResponse.createForError("order create failed");
        }
        orderVO.setOrderNo(order.getOrderNo());
        orderVO.setOrderItemVOList(orderItemVOList);
        orderVO.setCreateTime(order.getCreateTime().toString());
        orderVO.setAddressVO(address);
        orderVO.setStatus(CONSTANT.ORDER_STATUS.OPEN);
        orderVO.setPaymentType(CONSTANT.PAYMENT_TYPE.WECHAT); // default payment type is wechat pay
        return CommonResponse.createForSuccess(orderVO);
    }

    @Override
    public CommonResponse<OrderCartItemVO> getOrderDetail(Long userId) {

        return null;
    }

    @Override
    public CommonResponse<List<OrderVO>> getOrderList(Long userId) {
        return null;
    }

    @Override
    public CommonResponse<String> cancelOrder(Long userId, Long orderId) {
        return null;
    }

    private Long generateOrderNo(){
        return System.currentTimeMillis() + new Random().nextInt(1000);
    }
}
