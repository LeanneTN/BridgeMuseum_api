package com.example.bridgemuseum_api.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("product")
public class Product {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer category;
    private String name;
    private Double price;
    private String urlOfPic;
    private String description;
}
