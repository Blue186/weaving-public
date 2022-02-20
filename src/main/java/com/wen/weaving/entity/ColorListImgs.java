package com.wen.weaving.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("color_list_imgs")
public class ColorListImgs {
    @ApiModelProperty(name = "id",value = "图片的id")
    private Integer id;
    @ApiModelProperty(name = "status",value = "收藏状态，0表示未收藏1表示收藏了")
    private Integer status;
    @ApiModelProperty(name = "img",value = "图片路劲")
    private String img;
    @ApiModelProperty(name = "colorId",value = "colorList的id")
    private Integer colorId;
    @ApiModelProperty(name = "type",value = "类型，用于收藏时做判断")
    private Integer type;
}
