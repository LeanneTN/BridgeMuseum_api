package com.example.bridgemuseum_api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bridgemuseum_api.domain.Bridge;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BridgeMapper extends BaseMapper<Bridge> {
}
