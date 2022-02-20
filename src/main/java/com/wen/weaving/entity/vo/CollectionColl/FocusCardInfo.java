package com.wen.weaving.entity.vo.CollectionColl;

import com.wen.weaving.entity.FocusCard;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FocusCardInfo implements Serializable {
    @ApiModelProperty(name = "focusCard",value = "focusCard对象")
    private List<FocusCard> focusCard;
    @ApiModelProperty(name = "focus_coll",value = "focus中被收藏了的")
    private List<Integer> focus_coll;
}
