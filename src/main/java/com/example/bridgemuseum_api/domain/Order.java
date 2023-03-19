package com.example.bridgemuseum_api.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("order")
public class Order {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long orderNo;
    private Long userId;
    private BigDecimal paymentPrice;
    private Integer orderStatus;
    private Integer paymentType;
    private String country;
    private String province;
    private String city;
    private String postCode;
    private String preciseAddress;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private LocalDateTime closeTime;
    private LocalDateTime endTime;
    private LocalDateTime sendTime;
}
