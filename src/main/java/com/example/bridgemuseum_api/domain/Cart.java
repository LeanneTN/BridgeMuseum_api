package com.example.bridgemuseum_api.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("cart")
public class Cart {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer productId;
    private Long userId;
    private Integer quantity;
    private Double price;
    private Double totalPriceOfProduct;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    // item won't be put into order unless you checked it
    private Boolean checked;
}
