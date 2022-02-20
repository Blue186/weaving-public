package com.wen.weaving.entity.vo.CollectionColl;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 收藏接收信息
 */
@Data
public class CollR implements Serializable {
    @ApiModelProperty(name = "id",value = "收藏的信息id")
    private Integer id;
    @ApiModelProperty(name = "uid",value = "用户id")
    private Integer uid;
    @ApiModelProperty(name = "type",value = "类型")
    private Integer type;
    @ApiModelProperty(name = "nameId",value = "如果是color和xs,这里需要传送nameId,focus和lg不用传，为空就行")
    private Integer name_id;
}
