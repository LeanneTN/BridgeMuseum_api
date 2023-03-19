package com.example.bridgemuseum_api.service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
            orderProduct.setOrderNo(order.getOrderNo());
            orderProduct.setProductPrice(product.getPrice());
            orderProduct.setProductName(product.getName());
            orderProduct.setProductQuantity(cart.getQuantity());
            orderProduct.setProductId(product.getId());
            product.setQuantity(product.getQuantity() - cart.getQuantity());
            int role = orderProductMapper.insert(orderProduct);
            if (role == 0){
                return CommonResponse.createForError("create order failed");
            }
            role = productMapper.updateById(product);
            if (role == 0){
                return CommonResponse.createForError("product stock update failed");
            }
        }
        order.setOrderStatus(CONSTANT.ORDER_STATUS.OPEN);
        order.setCity(address.getCity());
        order.setPaymentType(CONSTANT.PAYMENT_TYPE.WECHAT); // default payment type is wechat pay
        order.setProvince(address.getProvince());
        order.setCountry(address.getCountry());
        order.setPostCode(address.getPostCode());
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
    public CommonResponse<OrderCartItemVO> getOrderDetail(Long userId, String orderNo) {
        OrderCartItemVO orderCartItemVO = new OrderCartItemVO();
        List<OrderItemVO> orderItemVOList = new ArrayList<>();
        List<OrderProduct> orderProducts = orderProductMapper.selectList(Wrappers.<OrderProduct>query()
                .eq("order_no", orderNo));
        Order order = orderMapper.selectOne(Wrappers.<Order>query().eq("order_no", orderNo));
        if (orderProducts.isEmpty()){
            return CommonResponse.createForError(ResponseCode.LIST_EMPTY.getCode(), ResponseCode.LIST_EMPTY.getDescription());
        }
        for (OrderProduct orderProduct : orderProducts){
            OrderItemVO orderItemVO = new OrderItemVO();
            orderItemVO.setTotalPrice(BigDecimalUtil.multiply(orderProduct.getProductQuantity().doubleValue(),
                    orderProduct.getProductPrice().doubleValue()));
            orderItemVO.setProductId(orderProduct.getProductId());
            orderItemVO.setCurrentPrice(orderProduct.getProductPrice());
            orderItemVO.setProductName(orderProduct.getProductName());
            orderItemVOList.add(orderItemVO);
        }
        orderCartItemVO.setOrderItemVOList(orderItemVOList);
        orderCartItemVO.setPaymentPrice(order.getPaymentPrice());
        return CommonResponse.createForSuccess(orderCartItemVO);
    }

    @Override
    public CommonResponse<List<OrderVO>> getOrderList(Long userId) {
        List<Order> orderList = orderMapper.selectList(Wrappers.<Order>query().eq("user_id", userId));
        List<OrderVO> orderVOS = new ArrayList<>();
        for (Order order : orderList){
            OrderVO orderVO = new OrderVO();
            orderVO.setPaymentPrice(order.getPaymentPrice());
            orderVO.setOrderNo(order.getOrderNo());
            orderVO.setCreateTime(order.getCreateTime().toString());
            orderVO.setUpdateTime(order.getUpdateTime().toString());
            orderVO.setCloseTime(order.getCloseTime().toString());
            orderVO.setEndTime(order.getEndTime().toString());
            orderVO.setPaymentType(order.getPaymentType());
            orderVO.setSendTime(orderVO.getSendTime());
            orderVO.setStatus(order.getOrderStatus());
            List<OrderItemVO> orderItemVOList = new ArrayList<>();
            List<OrderProduct> orderProducts = orderProductMapper.selectList(Wrappers.<OrderProduct>query()
                    .eq("order_no", order.getOrderNo()));
            for (OrderProduct orderProduct : orderProducts){
                OrderItemVO orderItemVO = new OrderItemVO();
                orderItemVO.setTotalPrice(BigDecimalUtil.multiply(orderProduct.getProductQuantity().doubleValue(),
                        orderProduct.getProductPrice().doubleValue()));
                orderItemVO.setProductId(orderProduct.getProductId());
                orderItemVO.setCurrentPrice(orderProduct.getProductPrice());
                orderItemVO.setProductName(orderProduct.getProductName());
                orderItemVOList.add(orderItemVO);
            }
            orderVO.setOrderItemVOList(orderItemVOList);
            orderVOS.add(orderVO);
        }
        return CommonResponse.createForSuccess(orderVOS);
    }

    @Override
    public CommonResponse<String> cancelOrder(Long userId, Long orderId) {
        Order order = orderMapper.selectById(orderId);
        if (order.getUserId() != userId){
            return CommonResponse.createForError("incorrect operation user");
        }
        order.setOrderStatus(CONSTANT.ORDER_STATUS.CANCEL);
        int role = orderMapper.updateById(order);
        if (role == 0){
            return CommonResponse.createForError("cancel order failed");
        }
        List<OrderProduct> orderProducts = orderProductMapper.selectList(Wrappers.<OrderProduct>query()
                .eq("order_no", order.getOrderNo()));
        for (OrderProduct orderProduct : orderProducts){
            Product product = productMapper.selectById(orderProduct.getProductId());
            product.setQuantity(product.getQuantity() + orderProduct.getProductQuantity());
        }
        return CommonResponse.createForSuccess("cancel order successfully");
    }

    @Override
    public CommonResponse<String> modifyStatus(Integer status, Long orderId) {
        if (status == CONSTANT.ORDER_STATUS.CANCEL){
            return CommonResponse.createForError("you can't cancel the order with this function");
        }
        Order order = orderMapper.selectById(orderId);
        if (order == null){
            return CommonResponse.createForError("order doesn't exist");
        }
        order.setOrderStatus(status);
        order.setUpdateTime(LocalDateTime.now());
        if (status == CONSTANT.ORDER_STATUS.TRANSPORT){
            order.setSendTime(LocalDateTime.now());
        }
        if (status == CONSTANT.ORDER_STATUS.CLOSE){
            order.setCloseTime(LocalDateTime.now());
        }
        if (status == CONSTANT.ORDER_STATUS.END){
            order.setEndTime(LocalDateTime.now());
        }
        int role = orderMapper.updateById(order);
        if (role == 0){
            return CommonResponse.createForError("update order status failed");
        }
        return CommonResponse.createForSuccess("update order status successfully");
    }

    @Override
    public CommonResponse<Object> deleteOrderInfoByUserId(Long userId, Long orderId) {
        Order order = orderMapper.selectById(orderId);
        order.setUpdateTime(LocalDateTime.now());
        if (order.getUserId() != userId){
            return CommonResponse.createForError("incorrect operation user");
        }
        List<OrderProduct> orderProducts = orderProductMapper.selectList(Wrappers.<OrderProduct>query()
                .eq("order_no", order.getOrderNo()));
        for (OrderProduct orderProduct : orderProducts){
            Product product = productMapper.selectById(orderProduct.getProductId());
            product.setQuantity(product.getQuantity() + orderProduct.getProductQuantity());
            int role = orderProductMapper.deleteById(orderProduct);
            if(role == 0){
                return CommonResponse.createForError("delete order information failed");
            }
        }
        int role = orderMapper.deleteById(order);
        if(role == 0){
            return CommonResponse.createForError("delete order information failed");
        }
        return CommonResponse.createForSuccess();
    }

    private Long generateOrderNo(){
        return System.currentTimeMillis() + new Random().nextInt(1000);
    }
}
