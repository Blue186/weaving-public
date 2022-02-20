package com.wen.weaving.entity.vo.CollectionColl;

import com.wen.weaving.entity.XsListImgs;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class XsListInfo implements Serializable {
    @ApiModelProperty(name = "id",value = "xsList的id")
    private Integer id;
    @ApiModelProperty(name = "xs_img",value = "图片路径")
    private String xs_img;
    @ApiModelProperty(name = "xs_name",value = "名字描述")
    private String xs_name;
    @ApiModelProperty(name = "List",value = "图片数组")
    private List<XsListImgs> List;
    @ApiModelProperty(name = "xs_coll",value = "xs被收藏了的信息")
    private List<Map<String,Object>> xs_coll;
}
