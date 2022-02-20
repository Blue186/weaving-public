package com.wen.weaving.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("xs_list_imgs")
public class XsListImgs {
    @ApiModelProperty(name = "id",value = "xsList的图片id")
    private Integer id;
    @ApiModelProperty(name = "status",value = "收藏状态，0表示未收藏，1表示收藏了")
    private Integer status;
    @ApiModelProperty(name = "img",value = "图片路径")
    private String img;
    @ApiModelProperty(name = "xsId",value = "xs_list的id")
    private Integer xsId;
    @ApiModelProperty(name = "type",value = "类型，用于收藏时做判断")
    private Integer type;
}
