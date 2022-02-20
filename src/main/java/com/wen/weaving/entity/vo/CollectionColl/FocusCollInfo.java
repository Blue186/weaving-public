package com.wen.weaving.entity.vo.CollectionColl;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class FocusCollInfo implements Serializable {
    @ApiModelProperty(name = "id",value = "聚焦")
    private Integer id;
    @ApiModelProperty(name = "img_url",value = "图片路劲")
    private String img_url;
    @ApiModelProperty(name = "link",value = "跳转链接")
    private String link;
    @ApiModelProperty(name = "cap",value = "标题")
    private String cap;
    @ApiModelProperty(name = "type",value = "类型")
    private Integer type;
    @ApiModelProperty(name = "fl_id",value = "focus和lg的id")
    private Integer fl_id;
    @ApiModelProperty(name = "status",value = "收藏状态")
    private Integer status;
}
