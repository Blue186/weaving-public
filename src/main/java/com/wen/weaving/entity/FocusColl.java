package com.wen.weaving.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**返回信息
 * focusColl包括focusList和lgList两个表
 */
@Data
@TableName("focus_coll")
public class FocusColl {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(name = "id",value = "聚焦")
    private Integer id;
    @ApiModelProperty(name = "uid",value = "用户id")
    private Integer uid;
//    @ApiModelProperty(name = "imgUrl",value = "图片路劲")
//    private String imgUrl;
//    @ApiModelProperty(name = "link",value = "跳转链接")
//    private String link;
//    @ApiModelProperty(name = "cap",value = "标题")
//    private String cap;
    @ApiModelProperty(name = "type",value = "类型")
    private Integer type;
    @ApiModelProperty(name = "flId",value = "focus和lg的id")
    private Integer flId;
    @ApiModelProperty(name = "status",value = "收藏状态")
    private Integer status;
}
