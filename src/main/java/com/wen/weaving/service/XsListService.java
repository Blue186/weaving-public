package com.wen.weaving.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wen.weaving.entity.XsList;
import com.wen.weaving.entity.vo.CollectionColl.XsListInfo;

import java.util.List;

public interface XsListService extends IService<XsList> {
    List<XsListInfo> getAllXsList(List<XsList> xsLists,Integer uid);
}
