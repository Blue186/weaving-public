package com.wen.weaving.entity.vo.MessageConll;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MessageInfo implements Serializable {
    @ApiModelProperty(name = "id",value = "用户的id")
    private Integer id;
    @ApiModelProperty(name = "content",value = "消息内容")
    private String content;
    @ApiModelProperty(name = "contactInfo",value = "联系信息qq/phone")
    private String contactInfo;
    @ApiModelProperty(name = "imgUrls",value = "图片链接")
    private List<String> imgUrls;
}
