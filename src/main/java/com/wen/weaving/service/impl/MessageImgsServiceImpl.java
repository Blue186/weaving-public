package com.wen.weaving.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wen.weaving.entity.MessageImgs;
import com.wen.weaving.mapper.MessageImgsMapper;
import com.wen.weaving.service.MessageImgsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessageImgsServiceImpl extends ServiceImpl<MessageImgsMapper, MessageImgs> implements MessageImgsService {
    @Resource
    private MessageImgsMapper messageImgsMapper;

    @Override
    public void insertImgUrls(List<String> imgUrls, Integer messageId) {
        for (String imgUrl : imgUrls) {
            MessageImgs messageImgs = new MessageImgs();
            messageImgs.setImgUrl(imgUrl);
            messageImgs.setMessId(messageId);
            baseMapper.insert(messageImgs);
        }
    }
}
