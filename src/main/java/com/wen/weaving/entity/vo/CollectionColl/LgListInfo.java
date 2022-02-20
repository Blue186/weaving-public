package com.wen.weaving.entity.vo.CollectionColl;

import com.wen.weaving.entity.LgList;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class LgListInfo implements Serializable {
    @ApiModelProperty(name = "id",value = "lgList的id")
    private List<LgList> lgLists;
    @ApiModelProperty(name = "lg_coll",value = "lg中被收藏了的")
    private List<Integer> lg_coll;
}
