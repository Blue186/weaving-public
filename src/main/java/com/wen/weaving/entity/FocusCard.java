package com.wen.weaving.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("focus_card")
public class FocusCard {
    @ApiModelProperty(name = "id",value = "FocusCard的id")
    private Integer id;
    @ApiModelProperty(name = "status",value = "收藏状态0表示未收藏1表示收藏")
    private Integer status;
    @ApiModelProperty(name = "cap",value = "标题描述")
    private String cap;
    @ApiModelProperty(name = "bgUrl",value = "背景图片链接")
    private String bgUrl;
    @ApiModelProperty(name = "link",value = "跳转链接")
    private String link;
    @ApiModelProperty(name = "type",value = "类型，用于收藏时做判断")
    private Integer type;
}
