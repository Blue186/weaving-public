package com.wen.weaving.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wen.weaving.entity.FocusCard;
import com.wen.weaving.entity.vo.CollectionColl.FocusCardInfo;

import java.util.List;

public interface FocusCardService extends IService<FocusCard> {
    FocusCardInfo getAllFocusCard(List<FocusCard> focusCards,Integer uid);
}
