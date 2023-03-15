package com.example.bridgemuseum_api.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author LeanneTN
 * Date: 2023/3/13
 */
@Data
@TableName("user")
public class User implements Serializable {
    private static final long serialVersionUID = -40356785423868312L;

    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String password;
    private String phone;
    // indentity of the user, admin or ordinary user
    private Integer role;
    private String province;
    private String city;
    // precise address of the user
    private String address;
}
