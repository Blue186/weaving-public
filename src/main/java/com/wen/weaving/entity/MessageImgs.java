package com.wen.weaving.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("message_imgs")
public class MessageImgs {
    @ApiModelProperty(name = "id",value = "图片id")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(name = "imgUrl",value = "图片路径")
    private String imgUrl;
    @ApiModelProperty(name = "messId",value = "对应建议反馈的id")
    private Integer messId;
}
