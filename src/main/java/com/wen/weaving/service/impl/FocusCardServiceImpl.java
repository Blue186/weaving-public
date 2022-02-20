package com.wen.weaving.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wen.weaving.entity.FocusCard;
import com.wen.weaving.entity.FocusColl;
import com.wen.weaving.entity.vo.CollectionColl.FocusCardInfo;
import com.wen.weaving.mapper.FocusCardMapper;
import com.wen.weaving.service.FocusCardService;
import com.wen.weaving.service.FocusCollService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class FocusCardServiceImpl extends ServiceImpl<FocusCardMapper,FocusCard> implements FocusCardService {
    @Resource
    private FocusCollService focusCollService;
    @Override
    public FocusCardInfo getAllFocusCard(List<FocusCard> focusCards,Integer uid) {
        FocusCardInfo focusCardList = new FocusCardInfo();
        focusCardList.setFocusCard(focusCards);
        QueryWrapper<FocusColl> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",uid);
        wrapper.eq("status",1);
        wrapper.eq("type",focusCards.get(0).getType());
        List<FocusColl> focusColls = focusCollService.getBaseMapper().selectList(wrapper);

        List<Integer> focus = new ArrayList<>();
        for (FocusColl focusColl : focusColls) {
            focus.add(focusColl.getFlId());
        }

        focusCardList.setFocus_coll(focus);
        System.out.println("--------focus"+focus);
        System.out.println("--------"+focusColls);
        return focusCardList;
    }
}
