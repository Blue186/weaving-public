package com.wen.weaving.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wen.weaving.entity.MaterialColl;
import com.wen.weaving.entity.vo.CollectionColl.MaterialCollInfo;

import java.util.List;

public interface MaterialCollService extends IService<MaterialColl> {
    List<MaterialCollInfo> getAllMaterialCollInfo(List<MaterialColl> materialColls);
}
