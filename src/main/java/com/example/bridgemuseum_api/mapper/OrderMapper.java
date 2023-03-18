package com.example.bridgemuseum_api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bridgemuseum_api.controller.OrderController;
import com.example.bridgemuseum_api.domain.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
