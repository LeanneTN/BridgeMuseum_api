package com.example.bridgemuseum_api.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("collection")
public class Collection {
    @TableId(type = IdType.AUTO)
    private Long id;
    // there are 4 kinds of items: articles, bridges, poems, products
    private Integer idOfItem;
    private Integer typeOfItem;
    private Long userId;
}
