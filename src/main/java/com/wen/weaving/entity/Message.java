package com.wen.weaving.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("message")
public class Message {
    @ApiModelProperty(name = "id",value = "消息id")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(name = "content",value = "消息内容")
    private String content;
    @ApiModelProperty(name = "contactInfo",value = "联系信息qq/phone")
    private String contactInfo;
    @ApiModelProperty(name = "uid",value = "用户id")
    private Integer uid;
}
