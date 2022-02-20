package com.wen.weaving.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("user")
public class User {
    @ApiModelProperty(name = "id",value = "用户id")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(name = "openid",value = "微信openid")
    private String openid;
    @ApiModelProperty(name = "sex",value = "性别")
    private Integer sex;
    @ApiModelProperty(name = "birthday",value = "生日")
    private String birthday;
}
