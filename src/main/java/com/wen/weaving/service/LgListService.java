package com.wen.weaving.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wen.weaving.entity.LgList;
import com.wen.weaving.entity.vo.CollectionColl.LgListInfo;

import java.util.List;

public interface LgListService extends IService<LgList> {
    LgListInfo getAllLgList(List<LgList> lgList,Integer uid);
}
