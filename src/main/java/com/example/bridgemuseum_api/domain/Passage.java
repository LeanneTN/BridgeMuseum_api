package com.example.bridgemuseum_api.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("passage")
public class Passage {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Long userId;
    // the id of the head of passage
    private Integer passageHeadId;
    private Integer ifHead;
    private String content;
}
