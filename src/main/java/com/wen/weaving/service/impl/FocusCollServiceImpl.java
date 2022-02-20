package com.wen.weaving.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wen.weaving.entity.FocusCard;
import com.wen.weaving.entity.FocusColl;
import com.wen.weaving.entity.LgList;
import com.wen.weaving.entity.vo.CollectionColl.FocusCollInfo;
import com.wen.weaving.mapper.FocusCollMapper;
import com.wen.weaving.service.FocusCardService;
import com.wen.weaving.service.FocusCollService;
import com.wen.weaving.service.LgListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class FocusCollServiceImpl extends ServiceImpl<FocusCollMapper, FocusColl> implements FocusCollService {
    @Resource
    private FocusCardService focusCardService;
    @Resource
    private LgListService lgListService;
    @Override
    public List<FocusCollInfo> getAllFocusCollInfo(List<FocusColl> focusColls) {
        List<FocusCollInfo> infos = new ArrayList<>();
        for (FocusColl focusColl : focusColls) {
            FocusCollInfo info = new FocusCollInfo();
            Integer type = focusColl.getType();
            if (type==1){//focus
                FocusCard focusCard = focusCardService.getBaseMapper().selectById(focusColl.getFlId());
                info.setId(focusColl.getId());
                info.setCap(focusCard.getCap());
                info.setFl_id(focusColl.getFlId());
                info.setImg_url(focusCard.getBgUrl());
                info.setLink(focusCard.getLink());
                info.setType(focusCard.getType());
                info.setStatus(focusCard.getStatus());
            }
            if (type==2){//lg
                LgList lgList = lgListService.getBaseMapper().selectById(focusColl.getId());
                info.setId(focusColl.getId());
                info.setCap(lgList.getLgCap());
                info.setFl_id(focusColl.getFlId());
                info.setImg_url(lgList.getLgImg());
                info.setLink(lgList.getLink());
                info.setType(lgList.getType());
                info.setStatus(lgList.getStatus());
            }
            infos.add(info);
        }
        return infos;
    }
}
