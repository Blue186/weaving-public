package com.wen.weaving.entity.vo.UserColl;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginInfo implements Serializable {
    @ApiModelProperty(name = "code",value = "微信code")
    private String code;
}
