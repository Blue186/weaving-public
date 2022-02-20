package com.wen.weaving.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**返回信息
 * materialColl包括colorList和xsList两个表
 */
@Data
@TableName("material_coll")
public class MaterialColl {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(name = "id",value = "收藏后id")
    private Integer id;
    @ApiModelProperty(name = "uid",value = "用户id")
    private Integer uid;
    @ApiModelProperty(name = "type",value = "类型")
    private Integer type;
    @ApiModelProperty(name = "cxId",value = "color和xs的id")
    private Integer cxId;
    @ApiModelProperty(name = "nameId",value = "image对应的nameId")
    private Integer nameId;
    @ApiModelProperty(name = "status",value = "收藏状态")
    private Integer status;
}
