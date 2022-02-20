package com.wen.weaving.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wen.weaving.entity.ColorList;
import com.wen.weaving.entity.ColorListImgs;
import com.wen.weaving.entity.MaterialColl;
import com.wen.weaving.entity.vo.CollectionColl.ColorListInfo;
import com.wen.weaving.mapper.ColorListMapper;
import com.wen.weaving.service.ColorListImgsService;
import com.wen.weaving.service.ColorListService;
import com.wen.weaving.service.MaterialCollService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ColorListServiceImpl extends ServiceImpl<ColorListMapper, ColorList> implements ColorListService {
    @Resource
    private ColorListImgsService colorListImgsService;
    @Resource
    private MaterialCollService materialCollService;
    @Override
    public List<ColorListInfo> getAllColorList(List<ColorList> colorLists,Integer uid) {
        List<ColorListInfo> infos = new ArrayList<>();
        for (ColorList colorList : colorLists) {
            ColorListInfo info = new ColorListInfo();
            info.setId(colorList.getId());
            info.setSk_img(colorList.getSkImg());
            info.setSk_name(colorList.getSkName());
            QueryWrapper<ColorListImgs> wrapper = new QueryWrapper<>();
            wrapper.eq("color_id",colorList.getId());
            wrapper.orderByAsc("id");
            List<ColorListImgs> colorListImgs = colorListImgsService.getBaseMapper().selectList(wrapper);
            info.setList(colorListImgs);

            QueryWrapper<MaterialColl> wrapper_M = new QueryWrapper<>();
            wrapper_M.eq("status",1);
            wrapper_M.eq("uid",uid);
            wrapper_M.eq("name_id",colorList.getId());
            wrapper_M.eq("type",colorListImgs.get(0).getType());
            List<MaterialColl> materialColls = materialCollService.getBaseMapper().selectList(wrapper_M);
            System.out.println("收藏的color--------"+materialColls);
            List<Map<String,Object>> list = new ArrayList<>();
            for (MaterialColl materialColl : materialColls) {
                    Map<String,Object> map = new HashMap<>();
                    map.put("name_id",materialColl.getNameId());
                    map.put("id",materialColl.getCxId());
                    list.add(map);
            }
            info.setSk_coll(list);
            infos.add(info);
        }
        return infos;
    }
}
