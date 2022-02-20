package com.wen.weaving.entity.vo.CollectionColl;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
public class MaterialCollInfo implements Serializable {
    @ApiModelProperty(name = "id",value = "收藏后id")
    private Integer id;
    @ApiModelProperty(name = "img_url",value = "图片路径")
    private String img_url;
    @ApiModelProperty(name = "type",value = "类型")
    private Integer type;
    @ApiModelProperty(name = "cx_id",value = "color和xs的id")
    private Integer cx_id;
    @ApiModelProperty(name = "name_id",value = "image对应的nameId")
    private Integer name_id;
    @ApiModelProperty(name = "status",value = "收藏状态")
    private Integer status;
}
