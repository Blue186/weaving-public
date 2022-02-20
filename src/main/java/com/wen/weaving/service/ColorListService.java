package com.wen.weaving.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wen.weaving.entity.ColorList;
import com.wen.weaving.entity.ColorListImgs;
import com.wen.weaving.entity.vo.CollectionColl.ColorListInfo;

import java.util.List;

public interface ColorListService extends IService<ColorList> {
    List<ColorListInfo> getAllColorList(List<ColorList> colorLists,Integer uid);
}
