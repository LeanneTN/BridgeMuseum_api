package com.example.bridgemuseum_api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bridgemuseum_api.domain.Poem;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author LeanneTN
 * date: 2023/3/16
 */
@Mapper
public interface PoemMapper extends BaseMapper<Poem> {
}
