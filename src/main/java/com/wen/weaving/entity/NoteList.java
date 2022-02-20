package com.wen.weaving.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("note_list")
public class NoteList {
    @ApiModelProperty(name = "id",value = "id")
    private Integer id;
    @ApiModelProperty(name = "des",value = "描述")
    private String des;
    @ApiModelProperty(name = "dur",value = "时长")
    private String dur;
    @ApiModelProperty(name = "vid",value = "vid/true/false")
    private Boolean vid;
    @ApiModelProperty(name = "src",value = "图片路径")
    private String src;
}
