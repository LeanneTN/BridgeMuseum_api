package com.example.bridgemuseum_api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bridgemuseum_api.domain.OrderProduct;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderProductMapper extends BaseMapper<OrderProduct> {
}
