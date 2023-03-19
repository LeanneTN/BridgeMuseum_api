package com.example.bridgemuseum_api.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("order_product")
public class OrderProduct {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String orderNo;
    private Integer productId;
    private String productName;
    private Integer productQuantity;
    private BigDecimal productPrice;
}
