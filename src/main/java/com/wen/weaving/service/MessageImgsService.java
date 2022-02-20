package com.wen.weaving.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wen.weaving.entity.MessageImgs;

import java.util.List;

public interface MessageImgsService extends IService<MessageImgs> {
    void insertImgUrls(List<String> imgUrls,Integer messageId);
}
