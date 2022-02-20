package com.wen.weaving.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("xs_list")
public class XsList {
    @ApiModelProperty(name = "id",value = "xsList的id")
    private Integer id;
    @ApiModelProperty(name = "xsImg",value = "图片路径")
    private String xsImg;
    @ApiModelProperty(name = "xsName",value = "名字描述")
    private String xsName;
}
