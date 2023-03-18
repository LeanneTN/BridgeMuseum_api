package com.example.bridgemuseum_api.service;

import com.example.bridgemuseum_api.VO.*;
import com.example.bridgemuseum_api.common.CommonResponse;
import com.example.bridgemuseum_api.domain.Order;

import java.util.List;

public interface OrderService {
    CommonResponse<OrderVO> addOrder(Long userId, OrderAddress address);

    CommonResponse<OrderCartItemVO> getOrderDetail(Long userId);

    CommonResponse<List<OrderVO>> getOrderList(Long userId);

    CommonResponse<String> cancelOrder(Long userId, Long orderId);
}
