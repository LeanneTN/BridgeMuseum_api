package com.example.bridgemuseum_api.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("bridge")
public class Bridge {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    // uploader's username
    private String username;
    @TableField("user_id")
    private Long userId;
    private String province;
    private String city;
    private String preciseAddress;
}
