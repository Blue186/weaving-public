package com.wen.weaving.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wen.weaving.entity.MaterialColl;
import com.wen.weaving.entity.XsList;
import com.wen.weaving.entity.XsListImgs;
import com.wen.weaving.entity.vo.CollectionColl.XsListInfo;
import com.wen.weaving.mapper.XsListMapper;
import com.wen.weaving.service.MaterialCollService;
import com.wen.weaving.service.XsListImgsService;
import com.wen.weaving.service.XsListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class XsListServiceImpl extends ServiceImpl<XsListMapper, XsList> implements XsListService {

    @Resource
    private XsListImgsService xsListImgsService;
    @Resource
    private MaterialCollService materialCollService;
    @Override
    public List<XsListInfo> getAllXsList(List<XsList> xsLists,Integer uid) {
        List<XsListInfo> infos = new ArrayList<>();
        for (XsList xsList : xsLists) {
            XsListInfo info = new XsListInfo();
            info.setId(xsList.getId());
            info.setXs_img(xsList.getXsImg());
            info.setXs_name(xsList.getXsName());
            QueryWrapper<XsListImgs> wrapper = new QueryWrapper<>();
            wrapper.eq("xs_id",xsList.getId());
            wrapper.orderByAsc("id");
            List<XsListImgs> xsListImgs = xsListImgsService.getBaseMapper().selectList(wrapper);
            info.setList(xsListImgs);

            QueryWrapper<MaterialColl> wrapper_M = new QueryWrapper<>();
            wrapper_M.eq("status",1);
            wrapper_M.eq("uid",uid);
            wrapper_M.eq("name_id",xsList.getId());
            wrapper_M.eq("type",xsListImgs.get(0).getType());
            List<MaterialColl> materialColls = materialCollService.getBaseMapper().selectList(wrapper_M);
            System.out.println("收藏的color--------"+materialColls);
            List<Map<String,Object>> list = new ArrayList<>();
            for (MaterialColl materialColl : materialColls) {
                Map<String,Object> map = new HashMap<>();
                map.put("name_id",materialColl.getNameId());
                map.put("id",materialColl.getCxId());
                list.add(map);
            }

            info.setXs_coll(list);

            infos.add(info);
        }
        return infos;
    }
}
