package com.wen.weaving.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wen.weaving.entity.FocusColl;
import com.wen.weaving.entity.LgList;
import com.wen.weaving.entity.vo.CollectionColl.LgListInfo;
import com.wen.weaving.mapper.LgListMapper;
import com.wen.weaving.service.FocusCollService;
import com.wen.weaving.service.LgListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class LgListServiceImpl extends ServiceImpl<LgListMapper, LgList> implements LgListService {
    @Resource
    private FocusCollService focusCollService;
    @Override
    public LgListInfo getAllLgList(List<LgList> lgList,Integer uid) {
        LgListInfo lgLists = new LgListInfo();
        lgLists.setLgLists(lgList);
        QueryWrapper<FocusColl> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",uid);
        wrapper.eq("type",lgList.get(0).getType());
        wrapper.eq("status",1);
        List<FocusColl> focusColls = focusCollService.getBaseMapper().selectList(wrapper);
        List<Integer> lg = new ArrayList<>();
        for (FocusColl focusColl : focusColls) {
            lg.add(focusColl.getFlId());
        }
        lgLists.setLg_coll(lg);
        System.out.println(lg+"---------lg");
        return lgLists;
    }
}
