package com.wen.weaving.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("color_list")
public class ColorList {
    @ApiModelProperty(name = "id",value = "colorList的id")
    private Integer id;
    @ApiModelProperty(name = "skImg",value = "图片路径")
    private String skImg;
    @ApiModelProperty(name = "skName",value = "名称")
    private String skName;
}
