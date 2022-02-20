package com.wen.weaving.entity.vo.CollectionColl;

import com.wen.weaving.entity.ColorListImgs;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class ColorListInfo implements Serializable {
    @ApiModelProperty(name = "id",value = "colorList的id")
    private Integer id;
    @ApiModelProperty(name = "sk_img",value = "图片路径")
    private String sk_img;
    @ApiModelProperty(name = "sk_name",value = "名称")
    private String sk_name;
    @ApiModelProperty(name = "List",value = "图片数组")
    private List<ColorListImgs> List;
    @ApiModelProperty(name = "sk_coll",value = "收藏了的信息")
    private List<Map<String,Object>> sk_coll;
}
