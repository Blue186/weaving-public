package com.wen.weaving.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wen.weaving.entity.ColorList;
import com.wen.weaving.entity.ColorListImgs;
import com.wen.weaving.entity.MaterialColl;
import com.wen.weaving.entity.XsListImgs;
import com.wen.weaving.entity.vo.CollectionColl.MaterialCollInfo;
import com.wen.weaving.mapper.MaterialCollMapper;
import com.wen.weaving.service.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MaterialCollServiceImpl extends ServiceImpl<MaterialCollMapper, MaterialColl> implements MaterialCollService {
    @Resource
    private ColorListService colorListService;
    @Resource
    private ColorListImgsService colorListImgsService;
    @Resource
    private XsListService xsListService;
    @Resource
    private XsListImgsService xsListImgsService;
    @Override
    public List<MaterialCollInfo> getAllMaterialCollInfo(List<MaterialColl> materialColls) {
        List<MaterialCollInfo> infos = new ArrayList<>();
        for (MaterialColl materialColl : materialColls) {
            Integer type = materialColl.getType();
            Integer nameId = materialColl.getNameId();
            MaterialCollInfo info = new MaterialCollInfo();
            if (type==3){//color
                QueryWrapper<ColorListImgs> wrapper = new QueryWrapper<>();
                wrapper.eq("id",materialColl.getCxId());
                wrapper.eq("color_id",nameId);
                ColorListImgs colorListImgs = colorListImgsService.getBaseMapper().selectOne(wrapper);
                info.setId(materialColl.getId());
                info.setCx_id(materialColl.getCxId());
                info.setImg_url(colorListImgs.getImg());
                info.setStatus(materialColl.getStatus());
                info.setType(colorListImgs.getType());
                info.setName_id(colorListImgs.getColorId());
            }
            if (type==4){//xs
                QueryWrapper<XsListImgs> wrapper = new QueryWrapper<>();
                wrapper.eq("id",materialColl.getCxId());
                wrapper.eq("xs_id",nameId);
                XsListImgs xsListImgs = xsListImgsService.getBaseMapper().selectOne(wrapper);
                info.setId(materialColl.getId());
                info.setCx_id(materialColl.getCxId());
                info.setImg_url(xsListImgs.getImg());
                info.setStatus(materialColl.getStatus());
                info.setType(xsListImgs.getType());
                info.setName_id(xsListImgs.getXsId());
            }
            infos.add(info);
        }
        return infos;
    }
}
