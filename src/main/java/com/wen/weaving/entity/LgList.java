package com.wen.weaving.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("lg_list")
public class LgList {
    @ApiModelProperty(name = "id",value = "lgList的id")
    private Integer id;
    @ApiModelProperty(name = "lgImg",value = "图片路径")
    private String lgImg;
    @ApiModelProperty(name = "lgCap",value = "lg标题")
    private String lgCap;
    @ApiModelProperty(name = "lgDes",value = "lg描述")
    private String lgDes;
    @ApiModelProperty(name = "link",value = "跳转路径")
    private String link;
    @ApiModelProperty(name = "status",value = "收藏状态，0表示未收藏，1表示收藏了的")
    private Integer status;
    @ApiModelProperty(name = "type",value = "类型，用于收藏时做判断")
    private Integer type;
}
