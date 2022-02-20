package com.wen.weaving.entity.vo.UserColl;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateInfo implements Serializable {
    @ApiModelProperty(name = "id",value = "用户id")
    private Integer id;
    @ApiModelProperty(name = "sex",value = "性别")
    private Integer sex;
    @ApiModelProperty(name = "birthday",value = "生日")
    private String birthday;
}
