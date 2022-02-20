package com.wen.weaving.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wen.weaving.entity.FocusColl;
import com.wen.weaving.entity.vo.CollectionColl.FocusCollInfo;

import java.util.List;

public interface FocusCollService extends IService<FocusColl> {
    List<FocusCollInfo> getAllFocusCollInfo(List<FocusColl> focusColls);
}
